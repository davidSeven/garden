package com.sky.inv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.inv.api.dto.InventoryStatementDto;
import com.sky.inv.api.enums.InventoryStatementOperateTypeEnum;
import com.sky.inv.api.model.Inventory;
import com.sky.inv.api.model.InventoryOccupation;
import com.sky.inv.api.model.InventoryStatement;
import com.sky.inv.dao.InventoryDao;
import com.sky.inv.service.InventoryOccupationService;
import com.sky.inv.service.InventoryService;
import com.sky.inv.service.InventoryStatementService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryDao, Inventory> implements InventoryService {
    private final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private InventoryStatementService inventoryStatementService;
    @Autowired
    private InventoryOccupationService inventoryOccupationService;

    @Transactional
    @Override
    public void in(InventoryStatementDto inventoryStatementDto) {
        // 行号默认1
        if (StringUtils.isEmpty(inventoryStatementDto.getInvoiceLineNo())) {
            inventoryStatementDto.setInvoiceLineNo("1");
        }
        int count = 0;
        try {
            // 处理接口幂等
            // 判断这个单号，这个行号有没有入库流水，如果有流水证明已经成功了
            LambdaQueryWrapper<InventoryStatement> inventoryStatementLambdaQueryWrapper = Wrappers.lambdaQuery();
            inventoryStatementLambdaQueryWrapper.eq(InventoryStatement::getCustomerCode, inventoryStatementDto.getCustomerCode());
            inventoryStatementLambdaQueryWrapper.eq(InventoryStatement::getWarehouseCode, inventoryStatementDto.getWarehouseCode());
            inventoryStatementLambdaQueryWrapper.eq(InventoryStatement::getProductCode, inventoryStatementDto.getProductCode());
            inventoryStatementLambdaQueryWrapper.eq(InventoryStatement::getInvoiceType, inventoryStatementDto.getInvoiceType());
            inventoryStatementLambdaQueryWrapper.eq(InventoryStatement::getInvoiceNo, inventoryStatementDto.getInvoiceNo());
            inventoryStatementLambdaQueryWrapper.eq(InventoryStatement::getInvoiceLineNo, inventoryStatementDto.getInvoiceLineNo());
            inventoryStatementLambdaQueryWrapper.eq(InventoryStatement::getBatchNo, inventoryStatementDto.getBatchNo());
            inventoryStatementLambdaQueryWrapper.eq(InventoryStatement::getOperateType, InventoryStatementOperateTypeEnum.IN.name());
            count = this.inventoryStatementService.count(inventoryStatementLambdaQueryWrapper);
            if (count == 0) {
                // 查询库存，如果库存不存在，新增一条，如果库存存在修改
                LambdaQueryWrapper<Inventory> inventoryLambdaQueryWrapper = Wrappers.lambdaQuery();
                inventoryLambdaQueryWrapper.eq(Inventory::getCustomerCode, inventoryStatementDto.getCustomerCode());
                inventoryLambdaQueryWrapper.eq(Inventory::getWarehouseCode, inventoryStatementDto.getWarehouseCode());
                inventoryLambdaQueryWrapper.eq(Inventory::getProductCode, inventoryStatementDto.getProductCode());
                inventoryLambdaQueryWrapper.eq(Inventory::getBatchNo, inventoryStatementDto.getBatchNo());
                Inventory inventory = this.getOne(inventoryLambdaQueryWrapper);
                if (null == inventory) {
                    Inventory insertInventory = BeanHelpUtil.convertDto(inventoryStatementDto, Inventory.class);
                    insertInventory.setAvailableQuantity(insertInventory.getQuantity());
                    insertInventory.setOccupationQuantity(BigDecimal.ZERO);
                    this.save(insertInventory);
                } else {
                    Inventory updateInventory = new Inventory();
                    updateInventory.setId(inventory.getId());
                    updateInventory.setQuantity(inventory.getQuantity().add(inventoryStatementDto.getQuantity()));
                    updateInventory.setAvailableQuantity(inventory.getAvailableQuantity().add(inventoryStatementDto.getQuantity()));
                    this.updateById(updateInventory);
                }
                // 新增流水
                InventoryStatement inventoryStatement = BeanHelpUtil.convertDto(inventoryStatementDto, InventoryStatement.class);
                inventoryStatement.setOperateType(InventoryStatementOperateTypeEnum.IN.name());
                this.inventoryStatementService.save(inventoryStatement);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            count = -1;
            throw e;
        } finally {
            this.setTransactionType(count);
        }
    }

    private void setTransactionType(int i) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        if (null != servletRequestAttributes) {
            HttpServletResponse response = servletRequestAttributes.getResponse();
            if (null != response) {
                response.setHeader("Transaction-Type", "" + i);
            }
        }
    }

    @Transactional
    @Override
    public void unIn(InventoryStatementDto inventoryStatementDto) {

    }

    @Override
    public boolean in(List<InventoryStatementDto> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return false;
    }

    @Transactional
    @Override
    public void out(InventoryStatementDto inventoryStatementDto) {
        // 行号默认1
        if (StringUtils.isEmpty(inventoryStatementDto.getInvoiceLineNo())) {
            inventoryStatementDto.setInvoiceLineNo("1");
        }
        int count = 0;
        try {
            // 处理接口幂等
            // 判断这个单号，这个行号有没有占用记录
            LambdaQueryWrapper<InventoryOccupation> inventoryOccupationLambdaQueryWrapper = Wrappers.lambdaQuery();
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getCustomerCode, inventoryStatementDto.getCustomerCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getWarehouseCode, inventoryStatementDto.getWarehouseCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getProductCode, inventoryStatementDto.getProductCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceType, inventoryStatementDto.getInvoiceType());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceNo, inventoryStatementDto.getInvoiceNo());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceLineNo, inventoryStatementDto.getInvoiceLineNo());
            InventoryOccupation inventoryOccupation = this.inventoryOccupationService.getOne(inventoryOccupationLambdaQueryWrapper);
            if (null != inventoryOccupation) {
                count = 1;
                // 库存库存减少
                Inventory inventory = this.getById(inventoryOccupation.getInventoryId());
                if (null == inventory) {
                    throw new CommonException(500, "出库失败，无库存信息");
                }
                Inventory updateInventory = new Inventory();
                updateInventory.setId(inventory.getId());
                updateInventory.setQuantity(inventory.getQuantity().subtract(inventoryOccupation.getQuantity()));
                updateInventory.setOccupationQuantity(inventory.getOccupationQuantity().subtract(inventoryOccupation.getQuantity()));
                this.updateById(updateInventory);
                // 删除占用
                this.inventoryOccupationService.physicalDeleteById(inventoryOccupation.getId());
                // 新增流水
                InventoryStatement inventoryStatement = BeanHelpUtil.convertDto(inventoryStatementDto, InventoryStatement.class);
                inventoryStatement.setOperateType(InventoryStatementOperateTypeEnum.OUT.name());
                inventoryStatement.setQuantity(inventoryOccupation.getQuantity().negate());
                inventoryStatement.setBatchNo(inventoryOccupation.getBatchNo());
                this.inventoryStatementService.save(inventoryStatement);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            count = -1;
            throw e;
        } finally {
            this.setTransactionType(count);
        }
    }

    @Transactional
    @Override
    public void occ(InventoryStatementDto inventoryStatementDto) {
        // 行号默认1
        if (StringUtils.isEmpty(inventoryStatementDto.getInvoiceLineNo())) {
            inventoryStatementDto.setInvoiceLineNo("1");
        }
        int count = 0;
        try {
            // 处理接口幂等
            // 判断这个单号，这个行号有没有占用记录
            LambdaQueryWrapper<InventoryOccupation> inventoryOccupationLambdaQueryWrapper = Wrappers.lambdaQuery();
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getCustomerCode, inventoryStatementDto.getCustomerCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getWarehouseCode, inventoryStatementDto.getWarehouseCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getProductCode, inventoryStatementDto.getProductCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceType, inventoryStatementDto.getInvoiceType());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceNo, inventoryStatementDto.getInvoiceNo());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceLineNo, inventoryStatementDto.getInvoiceLineNo());
            count = this.inventoryOccupationService.count(inventoryOccupationLambdaQueryWrapper);
            if (count == 0) {
                // 判断批次号是否为空
                Inventory inventory;
                if (StringUtils.isEmpty(inventoryStatementDto.getBatchNo())) {
                    // 查询库存
                    LambdaQueryWrapper<Inventory> inventoryLambdaQueryWrapper = Wrappers.lambdaQuery();
                    inventoryLambdaQueryWrapper.eq(Inventory::getCustomerCode, inventoryStatementDto.getCustomerCode());
                    inventoryLambdaQueryWrapper.eq(Inventory::getWarehouseCode, inventoryStatementDto.getWarehouseCode());
                    inventoryLambdaQueryWrapper.eq(Inventory::getProductCode, inventoryStatementDto.getProductCode());
                    // 入库日期升序，最早的在最前面
                    inventoryLambdaQueryWrapper.orderByAsc(Inventory::getWarehouseDate);
                    // 库存大于0的
                    inventoryLambdaQueryWrapper.gt(Inventory::getQuantity, 0);
                    List<Inventory> inventoryList = this.list(inventoryLambdaQueryWrapper);
                    if (CollectionUtils.isNotEmpty(inventoryList)) {
                        inventory = inventoryList.get(0);
                    } else {
                        inventory = null;
                    }
                } else {
                    // 查询库存
                    LambdaQueryWrapper<Inventory> inventoryLambdaQueryWrapper = Wrappers.lambdaQuery();
                    inventoryLambdaQueryWrapper.eq(Inventory::getCustomerCode, inventoryStatementDto.getCustomerCode());
                    inventoryLambdaQueryWrapper.eq(Inventory::getWarehouseCode, inventoryStatementDto.getWarehouseCode());
                    inventoryLambdaQueryWrapper.eq(Inventory::getProductCode, inventoryStatementDto.getProductCode());
                    inventoryLambdaQueryWrapper.eq(Inventory::getBatchNo, inventoryStatementDto.getBatchNo());
                    inventory = this.getOne(inventoryLambdaQueryWrapper);
                }
                if (null == inventory) {
                    throw new CommonException(500, "占用失败，[" + inventoryStatementDto.getProductCode() + "]无库存信息");
                }
                if (inventory.getAvailableQuantity().compareTo(inventoryStatementDto.getQuantity()) < 0) {
                    throw new CommonException(500, "占用失败，[" + inventoryStatementDto.getProductCode() + "]库存数量不足" + inventoryStatementDto.getQuantity());
                }
                // 修改占用数量
                Inventory updateInventory = new Inventory();
                updateInventory.setId(inventory.getId());
                updateInventory.setOccupationQuantity(inventory.getOccupationQuantity().add(inventoryStatementDto.getQuantity()));
                updateInventory.setAvailableQuantity(inventory.getAvailableQuantity().subtract(inventoryStatementDto.getQuantity()));
                this.updateById(updateInventory);
                // 新增占用记录
                InventoryOccupation inventoryOccupation = BeanHelpUtil.convertDto(inventoryStatementDto, InventoryOccupation.class);
                inventoryOccupation.setBatchNo(inventory.getBatchNo());
                inventoryOccupation.setInventoryId(inventory.getId());
                this.inventoryOccupationService.save(inventoryOccupation);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            count = -1;
            throw e;
        } finally {
            this.setTransactionType(count);
        }
    }

    @Transactional
    @Override
    public void unOcc(InventoryStatementDto inventoryStatementDto) {
        // 行号默认1
        if (StringUtils.isEmpty(inventoryStatementDto.getInvoiceLineNo())) {
            inventoryStatementDto.setInvoiceLineNo("1");
        }
        int count = 0;
        try {
            // 处理接口幂等
            // 判断这个单号，这个行号有没有占用记录
            LambdaQueryWrapper<InventoryOccupation> inventoryOccupationLambdaQueryWrapper = Wrappers.lambdaQuery();
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getCustomerCode, inventoryStatementDto.getCustomerCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getWarehouseCode, inventoryStatementDto.getWarehouseCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getProductCode, inventoryStatementDto.getProductCode());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceType, inventoryStatementDto.getInvoiceType());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceNo, inventoryStatementDto.getInvoiceNo());
            inventoryOccupationLambdaQueryWrapper.eq(InventoryOccupation::getInvoiceLineNo, inventoryStatementDto.getInvoiceLineNo());
            InventoryOccupation inventoryOccupation = this.inventoryOccupationService.getOne(inventoryOccupationLambdaQueryWrapper);
            if (null != inventoryOccupation) {
                count = 1;
                // 查询库存
                Inventory inventory = this.getById(inventoryOccupation.getInventoryId());
                if (null == inventory) {
                    throw new CommonException(500, "取消占用失败，无库存信息");
                }
                Inventory updateInventory = new Inventory();
                updateInventory.setId(inventory.getId());
                updateInventory.setOccupationQuantity(inventory.getOccupationQuantity().subtract(inventoryStatementDto.getQuantity()));
                updateInventory.setAvailableQuantity(inventory.getAvailableQuantity().add(inventoryStatementDto.getQuantity()));
                this.updateById(updateInventory);
                // 删除占用
                this.inventoryOccupationService.physicalDeleteById(inventoryOccupation.getId());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            count = -1;
            throw e;
        } finally {
            this.setTransactionType(count);
        }
    }
}
