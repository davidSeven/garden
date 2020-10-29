package com.sky.system.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @date 2020-10-28 028 13:53
 */
@Getter
@Setter
@ApiModel(value = "UserQueryDto", description = "用户查询信息")
public class UserQueryDto extends AbstractQueryDto {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "状态")
    private String state;
}
