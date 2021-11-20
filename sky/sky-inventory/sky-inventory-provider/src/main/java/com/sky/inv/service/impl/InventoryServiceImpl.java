package com.sky.inv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.inv.api.dto.InventoryStatementDto;
import com.sky.inv.api.enums.InventoryStatementOperateTypeEnum;
import com.sky.inv.api.model.Inventory;
import com.sky.inv.api.model.InventoryStatement;
import com.sky.inv.dao.InventoryDao;
import com.sky.inv.service.InventoryService;
import com.sky.inv.service.InventoryStatementService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryDao, Inventory> implements InventoryService {
    private final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private InventoryStatementService inventoryStatementService;

    @Transactional
    @Override
    public void in(InventoryStatementDto inventoryStatementDto) {
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
        int count = this.inventoryStatementService.count(inventoryStatementLambdaQueryWrapper);
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
                this.save(insertInventory);
            } else {
                Inventory updateInventory = new Inventory();
                updateInventory.setId(inventory.getId());
                updateInventory.setQuantity(inventory.getQuantity().add(inventoryStatementDto.getQuantity()));
                this.updateById(updateInventory);
            }
            // 新增流水
            InventoryStatement inventoryStatement = BeanHelpUtil.convertDto(inventoryStatementDto, InventoryStatement.class);
            inventoryStatement.setOperateType(InventoryStatementOperateTypeEnum.IN.name());
            this.inventoryStatementService.save(inventoryStatement);
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

    @Override
    public boolean out(Inventory inventory) {
        return false;
    }

    @Override
    public boolean occ(Inventory inventory) {
        return false;
    }

    @Override
    public boolean unOcc(Inventory inventory) {
        return false;
    }
}
