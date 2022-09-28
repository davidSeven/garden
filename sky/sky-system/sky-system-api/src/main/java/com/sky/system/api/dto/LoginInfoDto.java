package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @date 2020-10-29 029 16:01
 */
@Data
@ApiModel(value = "LoginInfoDto", description = "用户登录信息")
public class LoginInfoDto implements Serializable {

    @ApiModelProperty(value = "语言，逗号分割")
    private String language;

    @ApiModelProperty(value = "用户信息")
    private UserLoginDto user;
}
