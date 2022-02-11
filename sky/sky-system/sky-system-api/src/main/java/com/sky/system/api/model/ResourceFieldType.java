package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_resource_field_type")
public class ResourceFieldType extends BaseModel<ResourceFieldType> {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "资源服务ID")
    private Long resourceServiceId;

    @ApiModelProperty(value = "资源清单ID")
    private Long resourceDetailId;

    @ApiModelProperty(value = "资源字段ID")
    private Long resourceFieldId;
}
