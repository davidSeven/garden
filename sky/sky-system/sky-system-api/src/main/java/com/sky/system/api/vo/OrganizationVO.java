package com.sky.system.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "OrganizationVO", description = "OrganizationVO信息")
public class OrganizationVO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "父级编码")
    private String parentCode;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "路由")
    private String route;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "子级")
    private List<OrganizationVO> children;
}
