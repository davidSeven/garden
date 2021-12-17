package com.sky.system.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "LookupQueryDto", description = "LookupQueryDto信息")
public class LookupQueryDto extends AbstractQueryDto {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private String state;
}
