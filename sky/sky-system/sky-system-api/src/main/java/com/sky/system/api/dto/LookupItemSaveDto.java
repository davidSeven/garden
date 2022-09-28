package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "LookupItemSaveDto", description = "LookupItemSaveDto信息")
public class LookupItemSaveDto {

    @ApiModelProperty(value = "明细列表")
    private List<LookupItemDto> itemList;

    @ApiModelProperty(value = "删除的ID")
    private List<Long> deleteIds;
}
