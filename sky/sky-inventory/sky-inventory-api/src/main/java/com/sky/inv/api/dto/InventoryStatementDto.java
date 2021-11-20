package com.sky.inv.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class InventoryStatementDto {

    @NotBlank(message = "客户编码不能为空")
    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @NotBlank(message = "仓库编码不能为空")
    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @NotBlank(message = "产品编码不能为空")
    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @Min(value = 1, message = "数量不能小于1")
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    @NotNull(message = "入库日期不能为空")
    @ApiModelProperty(value = "入库日期")
    private Date warehouseDate;

    @NotBlank(message = "单据类型不能为空")
    @ApiModelProperty(value = "单据类型")
    private String invoiceType;

    @NotBlank(message = "单据号不能为空")
    @ApiModelProperty(value = "单据号")
    private String invoiceNo;

    @NotBlank(message = "单据行号不能为空")
    @ApiModelProperty(value = "单据行号")
    private String invoiceLineNo;

    @NotBlank(message = "批次号不能为空")
    @ApiModelProperty(value = "批次号")
    private String batchNo;
}
