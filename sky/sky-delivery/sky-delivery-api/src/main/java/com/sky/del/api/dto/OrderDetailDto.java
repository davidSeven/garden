package com.sky.del.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderDetailDto {

    @NotBlank(message = "申报品名不能为空")
    @ApiModelProperty(value = "申报品名")
    private String declaredName;

    @NotBlank(message = "产品编码不能为空")
    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @Digits(integer = 16, fraction = 4, message = "申报数量格式不正确")
    @DecimalMin(value = "1.0000", message = "申报数量不能小于1")
    @NotNull(message = "申报数量不能为空")
    @ApiModelProperty(value = "申报数量")
    private BigDecimal declaredQty;

}
