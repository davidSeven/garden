package com.forest.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @date 2020-10-19 019 13:39
 */
@Data
@ApiModel(value = "登录信息")
public class LoginDto {

    @NotBlank
    @ApiModelProperty(name = "帐号")
    private String account;

    @NotBlank
    @ApiModelProperty(name = "密码")
    private String password;

    @ApiModelProperty(name = "验证码")
    private String verificationCode;
}
