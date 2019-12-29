package com.stream.garden.warehouse.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.warehouse.dao.IWarehouseDao;
import com.stream.garden.warehouse.model.Warehouse;
import com.stream.garden.warehouse.service.IWarehouseService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-12-29 16:02
 */
@Service
public class WarehouseServiceImpl extends AbstractBaseService<Warehouse, String> implements IWarehouseService {
    public WarehouseServiceImpl(IWarehouseDao iWarehouseDao) {
        super(iWarehouseDao);
    }
}
