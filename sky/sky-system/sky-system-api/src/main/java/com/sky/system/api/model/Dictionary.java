package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2020-11-03 003 9:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dictionary")
public class Dictionary extends BaseModel<Dictionary> {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "父级编码")
    private String parentCode;

    @ApiModelProperty(value = "父级名称")
    private String parentName;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "路由")
    private String route;

    @ApiModelProperty(value = "子级数量")
    private Integer childrenSize;
}
