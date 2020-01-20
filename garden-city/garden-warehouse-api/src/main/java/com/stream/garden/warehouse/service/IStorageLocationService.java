package com.stream.garden.warehouse.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.warehouse.model.StorageLocation;

/**
 * @author garden
 * @date 2019-12-29 16:00
 */
public interface IStorageLocationService extends IBaseService<StorageLocation, String> {

    int insertLock(StorageLocation storageLocation) throws ApplicationException;
}
