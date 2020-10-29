package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @date 2020-10-28 028 13:47
 */
@Data
@ApiModel(value = "UserDto", description = "用户信息")
public class UserDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "登录失败次数")
    private Integer loginFailCount;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "登录时间")
    private Date loginDate;
}
