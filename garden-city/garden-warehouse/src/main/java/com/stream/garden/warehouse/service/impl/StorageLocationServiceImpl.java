package com.stream.garden.warehouse.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.warehouse.dao.IStorageLocationDao;
import com.stream.garden.warehouse.model.StorageLocation;
import com.stream.garden.warehouse.service.IStorageLocationService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2020-01-02 19:01
 */
@Service
public class StorageLocationServiceImpl extends AbstractBaseService<StorageLocation, String> implements IStorageLocationService {
    public StorageLocationServiceImpl(IStorageLocationDao iStorageLocationDao) {
        super(iStorageLocationDao);
    }
}
