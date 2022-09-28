package com.sky.base.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "ProductDto", description = "ProductDto信息")
public class ProductDto {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "长(cm)")
    private Double length;

    @ApiModelProperty(value = "宽(cm)")
    private Double width;

    @ApiModelProperty(value = "高(cm)")
    private Double height;

    @ApiModelProperty(value = "体积(cm3)")
    private Double volume;

    @ApiModelProperty(value = "重量(g)")
    private Double weight;

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "价值")
    private BigDecimal declaredValue;

    @ApiModelProperty(value = "编码集合")
    private List<String> codeList;
}
