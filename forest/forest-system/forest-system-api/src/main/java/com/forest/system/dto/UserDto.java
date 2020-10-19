package com.forest.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @date 2020-09-22 022 15:23
 */
@Data
public class UserDto {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty(name = "帐号")
    private String account;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "状态")
    private String state;
}
