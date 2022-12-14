package com.sky.inv.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.inv.api.dto.InventoryStatementBatchDto;
import com.sky.inv.api.dto.valid.InventoryStatementDtoGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface InventoryRemoteService {

    @PostMapping(value = "/inventory/in")
    ResponseDto<Boolean> in(@RequestBody @Validated(value = {InventoryStatementDtoGroup.In.class}) InventoryStatementBatchDto dto);

    @PostMapping(value = "/inventory/out")
    ResponseDto<Boolean> out(@RequestBody @Validated(value = {InventoryStatementDtoGroup.Default.class}) InventoryStatementBatchDto dto);

    @PostMapping(value = "/inventory/occ")
    ResponseDto<Boolean> occ(@RequestBody @Validated(value = {InventoryStatementDtoGroup.Default.class}) InventoryStatementBatchDto dto);

    @PostMapping(value = "/inventory/unOcc")
    ResponseDto<Boolean> unOcc(@RequestBody @Validated(value = {InventoryStatementDtoGroup.Default.class}) InventoryStatementBatchDto dto);
}
