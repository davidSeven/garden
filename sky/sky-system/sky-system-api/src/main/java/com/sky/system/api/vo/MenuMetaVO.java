package com.sky.system.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @date 2020-12-17 017 17:55
 */
@Data
@ApiModel(value = "MenuMetaVO", description = "MenuMetaVO信息")
public class MenuMetaVO implements Serializable {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图标")
    private String icon;

    public MenuMetaVO() {
    }

    public MenuMetaVO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
}
