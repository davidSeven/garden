package com.sky.inv.service;

import com.sky.inv.api.dto.InventoryStatementDto;

public interface InventoryLockService {

    void in(InventoryStatementDto inventoryStatementDto);

    void out(InventoryStatementDto inventoryStatementDto);

    void occ(InventoryStatementDto inventoryStatementDto);

    void unOcc(InventoryStatementDto inventoryStatementDto);
}
