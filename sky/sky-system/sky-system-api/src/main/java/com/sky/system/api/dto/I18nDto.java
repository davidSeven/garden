package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "I18nDto", description = "I18nDto信息")
public class I18nDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "语言类型")
    private String languageType;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "值")
    private String value;

}
