package com.sky.system.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "SerialNumberQueryDto", description = "查询对象")
public class SerialNumberQueryDto extends AbstractQueryDto {

    @ApiModelProperty(value = "业务编码")
    private String code;

    @ApiModelProperty(value = "流水号类型")
    private Integer type;
}
