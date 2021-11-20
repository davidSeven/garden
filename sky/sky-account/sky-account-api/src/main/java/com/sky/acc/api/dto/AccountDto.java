package com.sky.acc.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AccountDto {

    @NotBlank(message = "客户编码不能为空")
    @ApiModelProperty(value = "客户编码", required = true)
    private String customerCode;

    @ApiModelProperty(value = "是否冻结", example = "false")
    private Boolean freeze;

    @NotBlank(message = "币种不能为空")
    @ApiModelProperty(value = "币种", required = true)
    private String currency;

    @Digits(integer = 20, fraction = 4, message = "金额格式不正确")
    @DecimalMin(value = "1.0000", message = "金额不能小于1")
    @NotNull(message = "金额不能为空")
    @ApiModelProperty(value = "金额", required = true)
    private BigDecimal amount;

    @NotBlank(message = "单据类型不能为空")
    @ApiModelProperty(value = "单据类型", required = true)
    private String invoiceType;

    @NotBlank(message = "单据号不能为空")
    @ApiModelProperty(value = "单据号", required = true)
    private String invoiceNo;

    @NotBlank(message = "单据行号不能为空")
    @ApiModelProperty(value = "单据行号", required = true)
    private String invoiceLineNo;

}
