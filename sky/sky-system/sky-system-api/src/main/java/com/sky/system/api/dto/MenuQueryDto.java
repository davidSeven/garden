package com.sky.system.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @date 2020-12-16 016 13:33
 */
@Getter
@Setter
@ApiModel(value = "MenuQueryDto", description = "查询信息")
public class MenuQueryDto extends AbstractQueryDto {

    @ApiModelProperty(value = "名字")
    private String name;
}
