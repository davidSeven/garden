package com.sky.inv.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.inv.api.dto.InventoryStatementBatchDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface InventoryRemoteService {

    @PostMapping(value = "/inventory/in")
    ResponseDto<Boolean> in(@RequestBody @Validated InventoryStatementBatchDto dto);

    @PostMapping(value = "/inventory/out")
    ResponseDto<Boolean> out(@RequestBody @Validated InventoryStatementBatchDto dto);

    @PostMapping(value = "/inventory/occ")
    ResponseDto<Boolean> occ(@RequestBody @Validated InventoryStatementBatchDto dto);

    @PostMapping(value = "/inventory/unOcc")
    ResponseDto<Boolean> unOcc(@RequestBody @Validated InventoryStatementBatchDto dto);
}
