package com.stream.garden.warehouse.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.warehouse.service.IWarehouseLockService;
import com.stream.garden.warehouse.service.IWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author garden
 * @date 2020-01-20 10:05
 */
@Service
public class WarehouseLockServiceImpl implements IWarehouseLockService {
    private Logger logger = LoggerFactory.getLogger(WarehouseLockServiceImpl.class);

    @Autowired
    private IWarehouseService warehouseService;

    private Lock lock = new ReentrantLock();

    @Override
    public void addQuantity(String id, int quantity) throws ApplicationException {
        try {
            lock.lock();
            this.warehouseService.addQuantity(id, quantity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
