package com.sky.base.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bas_product")
public class Product extends BaseModel<Product> {

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
}
