package com.sky.inv.client.service;

import com.sky.inv.api.dto.InventoryStatementBatchDto;

public interface InventoryClientService {

    boolean in(InventoryStatementBatchDto dto);

    boolean out(InventoryStatementBatchDto dto);

    boolean occ(InventoryStatementBatchDto dto);

    boolean unOcc(InventoryStatementBatchDto dto);
}
