package com.sky.inv.service.impl;

import com.sky.framework.api.exception.CommonException;
import com.sky.inv.api.dto.InventoryStatementDto;
import com.sky.inv.service.InventoryLockService;
import com.sky.inv.service.InventoryWrapperService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryWrapperServiceImpl implements InventoryWrapperService {
    private final Logger logger = LoggerFactory.getLogger(InventoryWrapperServiceImpl.class);

    @Autowired
    private InventoryLockService inventoryLockService;

    @Override
    public boolean in(List<InventoryStatementDto> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (InventoryStatementDto inventoryStatementDto : list) {
            try {
                this.inventoryLockService.in(inventoryStatementDto);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                if (e instanceof CommonException) {
                    throw e;
                }
                throw new CommonException(500, "库存入库失败，" + e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean out(List<InventoryStatementDto> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (InventoryStatementDto inventoryStatementDto : list) {
            try {
                this.inventoryLockService.out(inventoryStatementDto);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                if (e instanceof CommonException) {
                    throw e;
                }
                throw new CommonException(500, "库存出库失败，" + e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean occ(List<InventoryStatementDto> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (InventoryStatementDto inventoryStatementDto : list) {
            try {
                this.inventoryLockService.occ(inventoryStatementDto);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                if (e instanceof CommonException) {
                    throw e;
                }
                throw new CommonException(500, "库存占用失败，" + e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean unOcc(List<InventoryStatementDto> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (InventoryStatementDto inventoryStatementDto : list) {
            try {
                this.inventoryLockService.unOcc(inventoryStatementDto);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                if (e instanceof CommonException) {
                    throw e;
                }
                throw new CommonException(500, "库存取消占用失败，" + e.getMessage());
            }
        }
        return true;
    }
}
