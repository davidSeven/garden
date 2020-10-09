package com.forest.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @date 2020-09-22 022 15:23
 */
@Data
public class UserDto {

    @ApiModelProperty(name = "名称")
    private String name;
}
