package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2020-12-16 016 11:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_menu")
public class Menu extends BaseModel<Menu> {

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "地址")
    private String path;

    @ApiModelProperty(value = "组件名称")
    private String component;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "子级数量")
    private Integer childrenSize;
}
