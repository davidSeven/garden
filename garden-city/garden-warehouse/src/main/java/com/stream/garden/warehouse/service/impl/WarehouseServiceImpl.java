package com.stream.garden.warehouse.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.cache.util.RedissLockUtil;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.warehouse.dao.IWarehouseDao;
import com.stream.garden.warehouse.model.Warehouse;
import com.stream.garden.warehouse.service.IWarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author garden
 * @date 2019-12-29 16:02
 */
@Service
public class WarehouseServiceImpl extends AbstractBaseService<Warehouse, IWarehouseDao> implements IWarehouseService {
    private Lock lock = new ReentrantLock();

    @Override
    public void addQuantityLock(String id, int quantity) throws ApplicationException {
        try {
            lock.lock();
            this.addQuantity(id, quantity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addQuantity(String id, int quantity) throws ApplicationException {
        Warehouse warehouse = super.get(id);
        this.addQuantity(warehouse);
    }

    @Override
    public void addQuantityRedisLock(String id, int quantity) throws ApplicationException {
        String lockKey = "addQuantityRedisLock";
        try {
            RedissLockUtil.lock(lockKey);
            this.addQuantity(id, quantity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        } finally {
            RedissLockUtil.unlock(lockKey);
        }
    }

    @Override
    public void addQuantityForUpdate(String id, int quantity) throws ApplicationException {
        Warehouse warehouseQuery = new Warehouse();
        warehouseQuery.setId(id);
        Warehouse warehouse = this.getMapper().getLock(warehouseQuery);
        this.addQuantity(warehouse);
    }

    private void addQuantity(Warehouse warehouse) throws ApplicationException {
        if (Objects.nonNull(warehouse)) {
            Integer code = Integer.valueOf(warehouse.getCode());
            code++;
            Warehouse updateWarehouse = new Warehouse();
            updateWarehouse.setId(warehouse.getId());
            updateWarehouse.setCode(String.valueOf(code));
            super.updateSelective(updateWarehouse);
        }
    }
}
