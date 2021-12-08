package com.sky.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.WarehouseDto;
import com.sky.base.api.dto.WarehouseQueryDto;
import com.sky.base.api.model.Warehouse;
import com.sky.base.api.remote.WarehouseRemoteService;
import com.sky.base.service.WarehouseService;
import com.sky.framework.api.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "仓库")
@ApiSort(300)
@RestController
public class WarehouseController implements WarehouseRemoteService {

    @Autowired
    private WarehouseService warehouseService;

    @Override
    public ResponseDto<Warehouse> get(Long id) {
        return new ResponseDto<>(warehouseService.getById(id));
    }

    @Override
    public ResponseDto<Warehouse> getByCode(String code) {
        return new ResponseDto<>(warehouseService.getByCode(code));
    }

    @Override
    public ResponseDto<Boolean> create(WarehouseDto dto) {
        return new ResponseDto<>(warehouseService.create(dto));
    }

    @Override
    public ResponseDto<Boolean> update(WarehouseDto dto) {
        return new ResponseDto<>(warehouseService.update(dto));
    }

    @Override
    public ResponseDto<Boolean> delete(WarehouseDto dto) {
        return new ResponseDto<>(warehouseService.removeById(dto.getId()));
    }

    @Override
    public ResponseDto<IPage<Warehouse>> page(WarehouseQueryDto dto) {
        return new ResponseDto<>(warehouseService.page(dto));
    }

    @Override
    public ResponseDto<List<Warehouse>> list(WarehouseDto dto) {
        return new ResponseDto<>(warehouseService.list(dto));
    }
}
