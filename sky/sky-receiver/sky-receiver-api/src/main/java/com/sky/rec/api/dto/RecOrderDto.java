package com.sky.rec.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RecOrderDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @ApiModelProperty(value = "明细")
    private List<RecOrderDetailDto> detailList;
}
