package com.sky.inv.client.service.impl;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.inv.api.dto.InventoryStatementBatchDto;
import com.sky.inv.client.feign.InventoryClientFeign;
import com.sky.inv.client.service.InventoryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryClientServiceImpl implements InventoryClientService {

    @Autowired
    private InventoryClientFeign inventoryClientFeign;

    @Override
    public boolean in(InventoryStatementBatchDto dto) {
        return ResponseDto.getDataBAndException(this.inventoryClientFeign.in(dto));
    }

    @Override
    public boolean out(InventoryStatementBatchDto dto) {
        return ResponseDto.getDataBAndException(this.inventoryClientFeign.out(dto));
    }

    @Override
    public boolean occ(InventoryStatementBatchDto dto) {
        return ResponseDto.getDataBAndException(this.inventoryClientFeign.occ(dto));
    }

    @Override
    public boolean unOcc(InventoryStatementBatchDto dto) {
        return ResponseDto.getDataBAndException(this.inventoryClientFeign.unOcc(dto));
    }
}
