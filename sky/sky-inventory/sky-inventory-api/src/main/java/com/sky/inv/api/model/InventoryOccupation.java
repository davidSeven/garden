package com.sky.inv.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("inv_inventory_occupation")
public class InventoryOccupation extends BaseModel<InventoryOccupation> {

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "单据类型")
    private String invoiceType;

    @ApiModelProperty(value = "单据号")
    private String invoiceNo;

    @ApiModelProperty(value = "单据行号")
    private String invoiceLineNo;

    @ApiModelProperty(value = "批次号")
    private String batchNo;
}
