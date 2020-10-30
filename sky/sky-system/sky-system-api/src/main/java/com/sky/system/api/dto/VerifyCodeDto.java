package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @date 2020-10-30 030 11:06
 */
@Data
@ApiModel(value = "VerifyCodeDto", description = "验证码信息")
public class VerifyCodeDto implements Serializable {

    @ApiModelProperty("是否需要验证码")
    private boolean needVc;

    @ApiModelProperty(value = "验证码")
    private String verifyCode;
}
