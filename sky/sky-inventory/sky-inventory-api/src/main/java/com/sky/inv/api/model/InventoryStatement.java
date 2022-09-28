package com.sky.inv.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("inv_inventory_statement")
public class InventoryStatement extends BaseModel<InventoryStatement> {

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value = "数量，IN正数，OUT负数")
    private BigDecimal quantity;

    @ApiModelProperty(value = "入库日期")
    private Date warehouseDate;

    @ApiModelProperty(value = "单据类型")
    private String invoiceType;

    @ApiModelProperty(value = "单据号")
    private String invoiceNo;

    @ApiModelProperty(value = "单据行号")
    private String invoiceLineNo;

    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @ApiModelProperty(value = "操作类型，IN，OUT")
    private String operateType;
}
