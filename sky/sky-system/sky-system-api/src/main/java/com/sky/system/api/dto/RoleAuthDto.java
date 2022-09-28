package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "RoleAuthDto", description = "RoleAuthDto信息")
public class RoleAuthDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "菜单ID")
    private List<Long> menuIdList;
}
