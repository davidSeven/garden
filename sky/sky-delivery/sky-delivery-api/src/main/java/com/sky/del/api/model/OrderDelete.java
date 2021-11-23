package com.sky.del.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("del_order_delete")
public class OrderDelete extends BaseModel<OrderDelete> {

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "处理次数")
    private Integer handleSize;

    @ApiModelProperty(value = "下一次处理时间")
    private Date nextHandleTime;

    @ApiModelProperty(value = "上一次失败原因")
    private String lastFailMessage;

    @ApiModelProperty(value = "上一次请求耗时时间")
    private Long lastRequestConsumeTime;

}
