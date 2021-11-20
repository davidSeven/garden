package com.sky.inv.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class InventoryStatementBatchDto {

    @Valid
    @NotNull(message = "集合不能为空")
    @ApiModelProperty(value = "集合")
    private List<InventoryStatementDto> list;
}
