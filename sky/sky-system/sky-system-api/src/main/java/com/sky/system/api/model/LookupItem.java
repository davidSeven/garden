package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_lookup_item")
public class LookupItem extends BaseModel<LookupItem> {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "语言类型")
    private String languageType;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "父级编码")
    private String parentCode;
}
