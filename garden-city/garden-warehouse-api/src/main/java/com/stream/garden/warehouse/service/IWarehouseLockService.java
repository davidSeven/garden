package com.stream.garden.warehouse.service;

import com.stream.garden.framework.api.exception.ApplicationException;

/**
 * @author garden
 * @date 2019-12-29 16:00
 */
public interface IWarehouseLockService {

    void addQuantity(String id, int quantity) throws ApplicationException;
}
