package com.sky.base.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "WarehouseDto", description = "WarehouseDto信息")
public class WarehouseDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "仓库编码集合")
    private List<String> warehouseCodeList;
}
