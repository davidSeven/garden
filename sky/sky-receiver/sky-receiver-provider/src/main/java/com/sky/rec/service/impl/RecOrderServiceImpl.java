package com.sky.rec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.inv.client.service.InventoryClientService;
import com.sky.rec.api.constant.ReceiverOrderConstant;
import com.sky.rec.api.dto.RecOrderDetailDto;
import com.sky.rec.api.dto.RecOrderDto;
import com.sky.rec.api.model.RecOrder;
import com.sky.rec.api.model.RecOrderDetail;
import com.sky.rec.dao.RecOrderDao;
import com.sky.rec.service.RecOrderDetailService;
import com.sky.rec.service.RecOrderService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RecOrderServiceImpl extends ServiceImpl<RecOrderDao, RecOrder> implements RecOrderService {
    private final Logger logger = LoggerFactory.getLogger(RecOrderServiceImpl.class);

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private SerialNumberClientService serialNumberClientService;
    @Autowired
    private InventoryClientService inventoryClientService;
    @Autowired
    private RecOrderDetailService orderDetailService;

    @Override
    public RecOrder getByOrderNo(String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
            return null;
        }
        LambdaQueryWrapper<RecOrder> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RecOrder::getOrderNo, orderNo);
        return super.getOne(queryWrapper);
    }

    @Transactional
    @Override
    public boolean create(RecOrderDto dto) {
        List<RecOrderDetailDto> detailList = dto.getDetailList();
        if (CollectionUtils.isEmpty(detailList)) {
            throw new CommonException(500, "明细不能为空");
        }
        String orderNo = this.serialNumberClientService.generateNumber(ReceiverOrderConstant.RECEIVER_ORDER_NO);
        // 订单信息
        RecOrder recOrder = BeanHelpUtil.convertDto(dto, RecOrder.class);
        recOrder.setOrderNo(orderNo);
        recOrder.setState(ReceiverOrderConstant.State.DRAFT.name());
        // 订单明细信息
        List<RecOrderDetail> orderDetails = new ArrayList<>();
        int lineNo = 1;
        for (RecOrderDetailDto detailDto : detailList) {
            RecOrderDetail detail = new RecOrderDetail();
            detail.setCustomerCode(recOrder.getCustomerCode());
            detail.setWarehouseCode(recOrder.getWarehouseCode());
            detail.setOrderNo(orderNo);
            detail.setBatchNo(recOrder.getBatchNo());
            detail.setLineNo(lineNo);
            detail.setProductCode(detailDto.getProductCode());
            detail.setQuantity(detailDto.getQuantity());
            detail.setWarehouseDate(detailDto.getWarehouseDate());
            orderDetails.add(detail);
            lineNo++;
        }
        // 保存订单
        this.save(recOrder);
        // 保存明细
        this.orderDetailService.saveBatch(orderDetails);
        return true;
    }

    @Transactional
    @Override
    public boolean saveAndSubmit(RecOrderDto dto) {
        return false;
    }

    @Transactional
    @Override
    public boolean submit(RecOrderDto dto) {
        String orderNo = dto.getOrderNo();
        if (StringUtils.isEmpty(orderNo)) {
            return false;
        }
        String lockKey = "RecOrder:" + orderNo;
        RLock lock = this.redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                RecOrder recOrder = this.getByOrderNo(orderNo);
                // 验证状态
                if (null != recOrder && ReceiverOrderConstant.State.DRAFT.name().equals(recOrder.getState())) {
                    // 修改状态为审核中
                    RecOrder updateRecOrder = new RecOrder();
                    updateRecOrder.setId(recOrder.getId());
                    updateRecOrder.setState(ReceiverOrderConstant.State.IN_PROCESS.name());
                    this.updateById(updateRecOrder);
                    return true;
                }
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean auth(RecOrderDto dto) {
        String orderNo = dto.getOrderNo();
        if (StringUtils.isEmpty(orderNo)) {
            return false;
        }
        String lockKey = "RecOrder:" + orderNo;
        RLock lock = this.redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                RecOrder recOrder = this.getByOrderNo(orderNo);
                // 验证状态
                if (null != recOrder && ReceiverOrderConstant.State.DRAFT.name().equals(recOrder.getState())) {
                    // 修改状态为审核中
                    RecOrder updateRecOrder = new RecOrder();
                    updateRecOrder.setId(recOrder.getId());
                    updateRecOrder.setState(ReceiverOrderConstant.State.IN_PROCESS.name());
                    this.updateById(updateRecOrder);
                    return true;
                }
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }
}
