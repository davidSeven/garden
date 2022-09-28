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
@ApiModel(value = "MenuVO", description = "MenuVO信息")
public class MenuVO implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "地址")
    private String path;

    @ApiModelProperty(value = "组件名称")
    private String component;

    @ApiModelProperty(value = "数据元")
    private MenuMetaVO meta;

    @ApiModelProperty(value = "子级")
    private List<MenuVO> children;

    public void builderMeta(String title, String icon) {
        this.meta = new MenuMetaVO(title, icon);
    }
}
