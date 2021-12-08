package com.sky.base.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.WarehouseDto;
import com.sky.base.api.dto.WarehouseQueryDto;
import com.sky.base.api.model.Warehouse;

import java.util.List;

public interface WarehouseClientService {

    Warehouse get(Long id);

    Warehouse getByCode(String code);

    boolean create(WarehouseDto dto);

    boolean update(WarehouseDto dto);

    boolean delete(WarehouseDto dto);

    IPage<Warehouse> page(WarehouseQueryDto dto);

    List<Warehouse> list(WarehouseDto dto);
}
