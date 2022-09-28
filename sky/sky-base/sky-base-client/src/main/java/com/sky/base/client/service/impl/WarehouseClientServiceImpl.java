package com.sky.base.client.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.WarehouseDto;
import com.sky.base.api.dto.WarehouseQueryDto;
import com.sky.base.api.model.Warehouse;
import com.sky.base.client.feign.WarehouseClientFeign;
import com.sky.base.client.service.WarehouseClientService;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseClientServiceImpl implements WarehouseClientService {

    @Autowired
    private WarehouseClientFeign warehouseClientFeign;

    @Override
    public Warehouse get(Long id) {
        return ResponseDto.getDataAndException(warehouseClientFeign.get(id));
    }

    @Override
    public Warehouse getByCode(String code) {
        return ResponseDto.getDataAndException(warehouseClientFeign.getByCode(code));
    }

    @Override
    public boolean create(WarehouseDto dto) {
        return ResponseDto.getDataBAndException(warehouseClientFeign.create(dto));
    }

    @Override
    public boolean update(WarehouseDto dto) {
        return ResponseDto.getDataBAndException(warehouseClientFeign.update(dto));
    }

    @Override
    public boolean delete(WarehouseDto dto) {
        return ResponseDto.getDataBAndException(warehouseClientFeign.delete(dto));
    }

    @Override
    public IPage<Warehouse> page(WarehouseQueryDto dto) {
        return ResponseDto.getDataAndException(warehouseClientFeign.page(dto));
    }

    @Override
    public List<Warehouse> list(WarehouseDto dto) {
        return ResponseDto.getDataAndException(warehouseClientFeign.list(dto));
    }
}
