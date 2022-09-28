package com.sky.system.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "ResourceDetailDto", description = "ResourceDetailDto信息")
public class ResourceDetailDto extends AbstractQueryDto {

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "资源应用ID")
    private Long resourceApplicationId;
}
