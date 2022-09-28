package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "LookupItemDto", description = "LookupItemDto信息")
public class LookupItemDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "语言类型")
    private String languageType;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "父级编码")
    private String parentCode;
}
