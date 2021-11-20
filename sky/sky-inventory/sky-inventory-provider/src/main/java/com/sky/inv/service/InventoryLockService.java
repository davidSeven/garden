package com.sky.inv.service;

import com.sky.inv.api.dto.InventoryStatementDto;

public interface InventoryLockService {

    void in(InventoryStatementDto inventoryStatementDto);
}
