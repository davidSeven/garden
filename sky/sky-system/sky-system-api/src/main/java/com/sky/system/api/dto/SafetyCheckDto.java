package com.sky.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @date 2020-10-29 029 16:41
 */
@Data
@ApiModel(value = "SafetyCheckDto", description = "安全检查信息")
public class SafetyCheckDto implements Serializable {

    @ApiModelProperty("是否需要验证码")
    private boolean needVc;

    @ApiModelProperty("验证token")
    private String vcToken;
}
