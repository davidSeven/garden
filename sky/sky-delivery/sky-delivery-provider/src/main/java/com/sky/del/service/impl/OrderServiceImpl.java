package com.sky.del.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.del.api.constant.DeliveryOrderConstant;
import com.sky.del.api.dto.OrderDeleteDto;
import com.sky.del.api.dto.OrderDetailDto;
import com.sky.del.api.dto.OrderDto;
import com.sky.del.api.model.Order;
import com.sky.del.api.model.OrderDetail;
import com.sky.del.dao.OrderDao;
import com.sky.del.service.OrderDeleteService;
import com.sky.del.service.OrderDetailService;
import com.sky.del.service.OrderService;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.inv.api.dto.InventoryStatementBatchDto;
import com.sky.inv.api.dto.InventoryStatementDto;
import com.sky.inv.client.service.InventoryClientService;
import com.sky.system.client.service.SerialNumberClientService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private SerialNumberClientService serialNumberClientService;
    @Autowired
    private InventoryClientService inventoryClientService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderDeleteService orderDeleteService;

    @Transactional
    @Override
    public boolean create(OrderDto dto) {
        List<OrderDetailDto> detailList = dto.getDetailList();
        if (CollectionUtils.isEmpty(detailList)) {
            throw new CommonException(500, "明细不能为空");
        }
        String orderNo = this.serialNumberClientService.generateNumber(DeliveryOrderConstant.DELIVERY_ORDER_NO);
        // 订单信息
        Order order = BeanHelpUtil.convertDto(dto, Order.class);
        order.setOrderNo(orderNo);
        // 订单明细信息
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<InventoryStatementDto> statementDtoList = new ArrayList<>();
        int lineNo = 1;
        for (OrderDetailDto detailDto : detailList) {
            OrderDetail detail = new OrderDetail();
            detail.setOrderNo(orderNo);
            detail.setLineNo("" + lineNo);
            detail.setDeclaredName(detailDto.getDeclaredName());
            detail.setProductCode(detailDto.getProductCode());
            detail.setDeclaredValue(BigDecimal.ZERO);
            detail.setDeclaredQty(detailDto.getDeclaredQty());
            detail.setWarehouseCode(order.getWarehouseCode());
            detail.setSiteCode(order.getSiteCode());
            orderDetails.add(detail);
            lineNo++;
            // 库存占用
            InventoryStatementDto statementDto = new InventoryStatementDto();
            statementDto.setCustomerCode(order.getCusCode());
            statementDto.setWarehouseCode(order.getWarehouseCode());
            statementDto.setProductCode(detail.getProductCode());
            statementDto.setInvoiceType("DELIVERY");
            statementDto.setInvoiceNo(detail.getOrderNo());
            statementDto.setInvoiceLineNo(detail.getLineNo());
            statementDto.setQuantity(detail.getDeclaredQty());
            statementDtoList.add(statementDto);
        }
        // 保存订单
        this.save(order);
        // 保存明细
        this.orderDetailService.saveBatch(orderDetails);
        // 库存
        InventoryStatementBatchDto inventoryStatementBatchDto = new InventoryStatementBatchDto();
        inventoryStatementBatchDto.setList(statementDtoList);
        this.inventoryClientService.occ(inventoryStatementBatchDto);
        return true;
    }

    @Transactional
    @Override
    public boolean delete(OrderDeleteDto dto) {
        List<String> orderNos = dto.getOrderNos();
        if (CollectionUtils.isEmpty(orderNos)) {
            throw new CommonException(500, "出库单号不能为空");
        }
        // 去重
        Set<String> orderNoSet = new HashSet<>(orderNos);
        List<String> newOrderNos = new ArrayList<>(orderNoSet);
        this.orderDeleteService.add(newOrderNos);
        return true;
    }

    @Transactional
    @Override
    public void delete(String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
            return;
        }
        String lockKey = "Order:delete:" + orderNo;
        RLock lock = this.redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(0, TimeUnit.SECONDS)) {
                LambdaQueryWrapper<Order> queryWrapper = Wrappers.lambdaQuery();
                queryWrapper.eq(Order::getOrderNo, orderNo);
                Order order = this.getOne(queryWrapper);
                if (null == order) {
                    return;
                }
                List<OrderDetail> orderDetailList = this.orderDetailService.listByOrderNo(orderNo);
                if (CollectionUtils.isEmpty(orderDetailList)) {
                    return;
                }
                List<InventoryStatementDto> statementDtoList = new ArrayList<>();
                for (OrderDetail detail : orderDetailList) {
                    // 库存占用
                    InventoryStatementDto statementDto = new InventoryStatementDto();
                    statementDto.setCustomerCode(order.getCusCode());
                    statementDto.setWarehouseCode(order.getWarehouseCode());
                    statementDto.setProductCode(detail.getProductCode());
                    statementDto.setInvoiceType("DELIVERY");
                    statementDto.setInvoiceNo(detail.getOrderNo());
                    statementDto.setInvoiceLineNo(detail.getLineNo());
                    statementDto.setQuantity(detail.getDeclaredQty());
                    statementDtoList.add(statementDto);
                }
                // 库存
                InventoryStatementBatchDto inventoryStatementBatchDto = new InventoryStatementBatchDto();
                inventoryStatementBatchDto.setList(statementDtoList);
                boolean unOcc = this.inventoryClientService.unOcc(inventoryStatementBatchDto);
                if (!unOcc) {
                    return;
                }
                // 删除明细
                this.orderDetailService.physicalDelete(orderNo);
                // 删除订单
                this.baseMapper.physicalDeleteById(order.getId());
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            Thread currentThread = Thread.currentThread();
            if (!currentThread.isInterrupted()) {
                currentThread.interrupt();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
