package com.sky.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.base.api.dto.WarehouseDto;
import com.sky.base.api.dto.WarehouseQueryDto;
import com.sky.base.api.model.Warehouse;

import java.util.List;

public interface WarehouseService extends IService<Warehouse> {

    Warehouse getByCode(String code);

    boolean create(WarehouseDto dto);

    boolean update(WarehouseDto dto);

    IPage<Warehouse> page(WarehouseQueryDto dto);

    List<Warehouse> list(WarehouseDto dto);
}
