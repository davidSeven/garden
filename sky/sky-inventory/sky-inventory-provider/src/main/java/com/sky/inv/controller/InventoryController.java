package com.sky.inv.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.inv.api.dto.InventoryStatementBatchDto;
import com.sky.inv.api.remote.InventoryRemoteService;
import com.sky.inv.service.InventoryWrapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "库存")
@ApiSort(100)
@RestController
public class InventoryController implements InventoryRemoteService {

    @Autowired
    private InventoryWrapperService inventoryWrapperService;

    @Override
    public ResponseDto<Boolean> in(InventoryStatementBatchDto dto) {
        boolean in = this.inventoryWrapperService.in(dto.getList());
        return new ResponseDto<>(in).ok();
    }

    @Override
    public ResponseDto<Boolean> out(InventoryStatementBatchDto dto) {
        boolean out = this.inventoryWrapperService.out(dto.getList());
        return new ResponseDto<>(out).ok();
    }

    @Override
    public ResponseDto<Boolean> occ(InventoryStatementBatchDto dto) {
        boolean occ = this.inventoryWrapperService.occ(dto.getList());
        return new ResponseDto<>(occ).ok();
    }

    @Override
    public ResponseDto<Boolean> unOcc(InventoryStatementBatchDto dto) {
        boolean unOcc = this.inventoryWrapperService.unOcc(dto.getList());
        return new ResponseDto<>(unOcc).ok();
    }
}
