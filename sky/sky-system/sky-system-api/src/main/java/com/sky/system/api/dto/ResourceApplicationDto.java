package com.sky.system.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "ResourceApplicationDto", description = "ResourceApplicationDto信息")
public class ResourceApplicationDto extends AbstractQueryDto {

    @ApiModelProperty(value = "名称")
    private String name;
}
