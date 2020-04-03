package com.stream.garden.warehouse.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.warehouse.model.Warehouse;

/**
 * @author garden
 * @date 2019-12-29 16:00
 */
public interface IWarehouseService extends IBaseService<Warehouse, String> {

    void addQuantityLock(String id, int quantity) throws ApplicationException;

    void addQuantity(String id, int quantity) throws ApplicationException;

    void addQuantityRedisLock(String id, int quantity) throws ApplicationException;

    void addQuantityForUpdate(String id, int quantity) throws ApplicationException;
}
