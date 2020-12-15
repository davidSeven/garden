package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @date 2020-10-29 029 16:01
 */
@Data
@ApiModel(value = "UserLoginDto", description = "用户登录信息")
public class UserLoginDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "登录时间")
    private Date loginDate;

    @ApiModelProperty(value = "登录token")
    private String token;

    @ApiModelProperty(value = "状态")
    private String state;
}
