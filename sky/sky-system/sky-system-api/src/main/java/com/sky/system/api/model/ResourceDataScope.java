package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_resource_data_scope")
public class ResourceDataScope extends BaseModel<ResourceDataScope> {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "字段")
    private String field;

    @ApiModelProperty(value = "字段值")
    private String fieldValue;

    @ApiModelProperty(value = "条件类型")
    private String conditionType;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "资源服务ID")
    private Long resourceServiceId;

    @ApiModelProperty(value = "资源清单ID")
    private Long resourceDetailId;
}
