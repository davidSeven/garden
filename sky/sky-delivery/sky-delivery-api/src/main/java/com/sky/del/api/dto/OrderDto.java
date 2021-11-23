package com.sky.del.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDto {

    @NotBlank(message = "仓库编号不能为空")
    @ApiModelProperty(value = "仓库编号")
    private String warehouseCode;

    @NotBlank(message = "网点编号不能为空")
    @ApiModelProperty(value = "网点编号")
    private String siteCode;

    @NotBlank(message = "客户编号不能为空")
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

    @Valid
    @NotNull(message = "明细不能为空")
    @ApiModelProperty(value = "申报人")
    private List<OrderDetailDto> detailList;
}
