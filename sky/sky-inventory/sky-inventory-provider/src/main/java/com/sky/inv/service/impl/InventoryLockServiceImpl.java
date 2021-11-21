package com.sky.inv.service.impl;

import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.Worker;
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

    private String getLockKey(InventoryStatementDto inventoryStatementDto) {
        String key = "Inventory:" + inventoryStatementDto.getCustomerCode() + ":" + inventoryStatementDto.getWarehouseCode() + ":" + inventoryStatementDto.getProductCode() + ":" + inventoryStatementDto.getBatchNo();
        return key;
    }

    private void lock(InventoryStatementDto inventoryStatementDto, String errMsg, Worker worker) {
        RLock lock = redissonClient.getLock(getLockKey(inventoryStatementDto));
        try {
            if (lock.tryLock(15, TimeUnit.SECONDS)) {
                worker.execute();
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            // Thread.currentThread().interrupt();
            throw new CommonException(500, errMsg + "，" + e.getMessage());
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }


    @Override
    public void in(InventoryStatementDto inventoryStatementDto) {
        this.lock(inventoryStatementDto, "入库失败", new Worker() {
            @Override
            public void execute() {
                inventoryService.in(inventoryStatementDto);
            }
        });
    }

    @Override
    public void out(InventoryStatementDto inventoryStatementDto) {
        this.lock(inventoryStatementDto, "出库失败", new Worker() {
            @Override
            public void execute() {
                inventoryService.out(inventoryStatementDto);
            }
        });
    }

    @Override
    public void occ(InventoryStatementDto inventoryStatementDto) {
        this.lock(inventoryStatementDto, "占用失败", new Worker() {
            @Override
            public void execute() {
                inventoryService.occ(inventoryStatementDto);
            }
        });
    }

    @Override
    public void unOcc(InventoryStatementDto inventoryStatementDto) {
        this.lock(inventoryStatementDto, "取消占用失败", new Worker() {
            @Override
            public void execute() {
                inventoryService.unOcc(inventoryStatementDto);
            }
        });
    }
}
