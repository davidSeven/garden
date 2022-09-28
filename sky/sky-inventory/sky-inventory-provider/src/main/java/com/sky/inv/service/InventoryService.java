package com.sky.inv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.inv.api.dto.InventoryStatementDto;
import com.sky.inv.api.model.Inventory;

import java.util.List;

public interface InventoryService extends IService<Inventory> {

    void in(InventoryStatementDto inventoryStatementDto);

    void unIn(InventoryStatementDto inventoryStatementDto);

    boolean in(List<InventoryStatementDto> list);

    void out(InventoryStatementDto inventoryStatementDto);

    void occ(InventoryStatementDto inventoryStatementDto);

    void unOcc(InventoryStatementDto inventoryStatementDto);
}
