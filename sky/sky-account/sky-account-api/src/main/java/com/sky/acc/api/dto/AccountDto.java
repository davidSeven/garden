package com.sky.acc.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Size(min = 1)
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
