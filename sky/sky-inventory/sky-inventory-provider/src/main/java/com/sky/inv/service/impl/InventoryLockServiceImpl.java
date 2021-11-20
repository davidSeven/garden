package com.sky.inv.service.impl;

import com.sky.framework.api.exception.CommonException;
import com.sky.inv.api.dto.InventoryStatementDto;
import com.sky.inv.service.InventoryLockService;
import com.sky.inv.service.InventoryService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class InventoryLockServiceImpl implements InventoryLockService {
    private final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private InventoryService inventoryService;

    @Override
    public void in(InventoryStatementDto inventoryStatementDto) {
        String key = "Inventory:" + inventoryStatementDto.getCustomerCode() + ":" + inventoryStatementDto.getWarehouseCode() + ":" + inventoryStatementDto.getProductCode() + ":" + inventoryStatementDto.getBatchNo();
        RLock lock = redissonClient.getLock(key);
        try {
            if (lock.tryLock(15, TimeUnit.SECONDS)) {
                this.inventoryService.in(inventoryStatementDto);
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            // Thread.currentThread().interrupt();
            throw new CommonException(500, "入库执行失败，" + e.getMessage());
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
