package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("del_order_detail")
public class OrderDetail extends BaseModel<OrderDetail> {

    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "箱号")
    private String boxNo;

    @ApiModelProperty(value = "申报品名")
    private String declaredName;

    @ApiModelProperty(value = "品名")
    private String productName;

    @ApiModelProperty(value = "申报价值")
    private BigDecimal declaredValue;

    @ApiModelProperty(value = "申报数量")
    private Integer declaredQty;

    @ApiModelProperty(value = "仓库编号")
    private String warehouseCode;

    @ApiModelProperty(value = "网点编号")
    private String siteCode;

}
