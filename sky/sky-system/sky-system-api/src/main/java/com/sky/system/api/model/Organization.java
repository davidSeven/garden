package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_organization")
public class Organization extends BaseModel<Organization> {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "父级编码")
    private String parentCode;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "路由")
    private String route;

    @ApiModelProperty(value = "级别")
    private Integer level;
}
