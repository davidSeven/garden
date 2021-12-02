package com.sky.rec.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("rec_order")
public class RecOrder extends BaseModel<RecOrder> {

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @ApiModelProperty(value = "状态")
    private String state;
}
