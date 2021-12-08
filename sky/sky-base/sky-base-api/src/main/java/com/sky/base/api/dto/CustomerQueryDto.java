package com.sky.base.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "CustomerQueryDto", description = "CustomerQueryDto信息")
public class CustomerQueryDto extends AbstractQueryDto {

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "客户编码模糊")
    private String customerCodeLike;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String mobilePhone;
}
