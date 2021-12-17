package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @date 2020-11-03 003 9:59
 */
@Data
@ApiModel(value = "DictionaryDto", description = "数据字典信息")
public class DictionaryDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @NotBlank(message = "{common.notBlank}")
    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "父级编码")
    private String parentCode;

    @ApiModelProperty(value = "父级名称")
    private String parentName;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "顺序")
    private Long sort;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "备注")
    private String remark;
}
