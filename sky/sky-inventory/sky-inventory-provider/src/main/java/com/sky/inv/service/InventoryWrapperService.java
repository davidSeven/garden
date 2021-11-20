package com.sky.inv.service;

import com.sky.inv.api.dto.InventoryStatementDto;

import java.util.List;

public interface InventoryWrapperService {

    boolean in(List<InventoryStatementDto> list);
}
