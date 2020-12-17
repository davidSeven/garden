package com.sky.system.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @date 2020-12-17 017 17:55
 */
@Data
@ApiModel(value = "LoginDto", description = "登录信息")
public class MenuVO implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "地址")
    private String path;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "子级")
    private List<MenuVO> children;
}
