package com.sky.base.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "ProductQueryDto", description = "ProductQueryDto信息")
public class ProductQueryDto extends AbstractQueryDto {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "编码模糊")
    private String codeLike;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

}
