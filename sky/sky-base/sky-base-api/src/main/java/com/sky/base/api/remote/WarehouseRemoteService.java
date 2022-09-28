package com.sky.base.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.WarehouseDto;
import com.sky.base.api.dto.WarehouseQueryDto;
import com.sky.base.api.model.Warehouse;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface WarehouseRemoteService {

    @GetMapping(value = "/warehouse/{id}")
    ResponseDto<Warehouse> get(@PathVariable(value = "id") Long id);

    @GetMapping(value = "/warehouse/code/{code}")
    ResponseDto<Warehouse> getByCode(@PathVariable(value = "code") String code);

    @PostMapping(value = "/warehouse")
    ResponseDto<Boolean> create(@RequestBody WarehouseDto dto);

    @PutMapping(value = "/warehouse")
    ResponseDto<Boolean> update(@RequestBody @Validated WarehouseDto dto);

    @DeleteMapping(value = "/warehouse")
    ResponseDto<Boolean> delete(@RequestBody @Validated WarehouseDto dto);

    @PostMapping(value = "/warehouse/page")
    ResponseDto<IPage<Warehouse>> page(@RequestBody WarehouseQueryDto dto);

    @PostMapping(value = "/warehouse/list")
    ResponseDto<List<Warehouse>> list(@RequestBody WarehouseDto dto);
}
