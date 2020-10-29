package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @date 2020-10-29 029 16:06
 */
@Data
@ApiModel(value = "LoginDto", description = "登录信息")
public class LoginDto implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "编码")
    private String code;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String verifyCode;

    @ApiModelProperty(value = "验证token")
    private String vcToken;
}
