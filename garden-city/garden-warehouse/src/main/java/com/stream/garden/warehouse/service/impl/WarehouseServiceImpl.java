package com.stream.garden.warehouse.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.warehouse.dao.IWarehouseDao;
import com.stream.garden.warehouse.model.Warehouse;
import com.stream.garden.warehouse.service.IWarehouseService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author garden
 * @date 2019-12-29 16:02
 */
@Service
public class WarehouseServiceImpl extends AbstractBaseService<Warehouse, String> implements IWarehouseService {
    private Lock lock = new ReentrantLock();

    public WarehouseServiceImpl(IWarehouseDao iWarehouseDao) {
        super(iWarehouseDao);
    }

    private IWarehouseDao getDao() {
        return (IWarehouseDao) super.baseMapper;
    }

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

    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addQuantity(String id, int quantity) throws ApplicationException {
        Warehouse warehouse = super.get(id);
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
