package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("del_order")
public class Order extends BaseModel<Order> {

    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "箱号")
    private String boxNo;

    @ApiModelProperty(value = "每箱规格")
    private String boxSpec;

    @ApiModelProperty(value = "每箱长")
    private BigDecimal boxLength;

    @ApiModelProperty(value = "每箱宽")
    private BigDecimal boxWidth;

    @ApiModelProperty(value = "每箱高")
    private BigDecimal boxHeight;

    @ApiModelProperty(value = "每箱重量")
    private BigDecimal boxWeight;

    @ApiModelProperty(value = "每箱体积")
    private BigDecimal boxVolume;

    @ApiModelProperty(value = "包裹数量")
    private Integer parcelsNum;

    @ApiModelProperty(value = "包裹规格")
    private String parcelsSpec;

    @ApiModelProperty(value = "包裹重量")
    private BigDecimal parcelsWeight;

    @ApiModelProperty(value = "包裹体积")
    private BigDecimal parcelsVolume;

    @ApiModelProperty(value = "仓库编号")
    private String warehouseCode;

    @ApiModelProperty(value = "网点编号")
    private String siteCode;

    @ApiModelProperty(value = "客户编号")
    private String cusCode;

    @ApiModelProperty(value = "发件人")
    private String sender;

    @ApiModelProperty(value = "发件电话")
    private String senderPhone;

    @ApiModelProperty(value = "发件人邮编")
    private String senderPostcode;

    @ApiModelProperty(value = "发件人国家")
    private String senderCountry;

    @ApiModelProperty(value = "发件人省份")
    private String senderProvince;

    @ApiModelProperty(value = "发件人城市")
    private String senderCity;

    @ApiModelProperty(value = "发件人地址")
    private String senderAddress;

    @ApiModelProperty(value = "收件人")
    private String receiver;

    @ApiModelProperty(value = "收件电话")
    private String receiverPhone;

    @ApiModelProperty(value = "收件人邮编")
    private String receiverPostcode;

    @ApiModelProperty(value = "收件人国家")
    private String receiverCountry;

    @ApiModelProperty(value = "收件人省份")
    private String receiverProvince;

    @ApiModelProperty(value = "收件人城市")
    private String receiverCity;

    @ApiModelProperty(value = "收件人地址")
    private String receiverAddress;

    @ApiModelProperty(value = "渠道")
    private String channel;

    @ApiModelProperty(value = "服务代码")
    private String serviceCode;

    @ApiModelProperty(value = "目的地代码")
    private String destCode;

    @ApiModelProperty(value = "申报人")
    private String declareUserName;

    @ApiModelProperty(value = "申报人电话")
    private String declareUserTelephone;

    @ApiModelProperty(value = "申报人")
    private String declareUser;

    @ApiModelProperty(value = "申报价值")
    private BigDecimal declaredValue;

}
