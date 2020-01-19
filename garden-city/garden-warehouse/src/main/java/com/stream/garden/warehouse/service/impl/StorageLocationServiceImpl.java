package com.stream.garden.warehouse.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.warehouse.dao.IStorageLocationDao;
import com.stream.garden.warehouse.model.StorageLocation;
import com.stream.garden.warehouse.model.Warehouse;
import com.stream.garden.warehouse.service.IStorageLocationService;
import com.stream.garden.warehouse.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2020-01-02 19:01
 */
@Service
public class StorageLocationServiceImpl extends AbstractBaseService<StorageLocation, String> implements IStorageLocationService {

    @Autowired
    private IWarehouseService warehouseService;

    public StorageLocationServiceImpl(IStorageLocationDao iStorageLocationDao) {
        super(iStorageLocationDao);
    }

    @Override
    public int insert(StorageLocation storageLocation) throws ApplicationException {
        // code + 1
        Warehouse warehouse = this.warehouseService.get(storageLocation.getWarehouseId());
        if (null == warehouse) {
            throw new ApplicationException("仓库信息不存在");
        }
        Integer code = Integer.valueOf(warehouse.getCode());
        code++;
        Warehouse updateWarehouse = new Warehouse();
        updateWarehouse.setId(warehouse.getId());
        updateWarehouse.setCode(String.valueOf(code));
        this.warehouseService.updateSelective(updateWarehouse);
        return super.insert(storageLocation);
    }
}
