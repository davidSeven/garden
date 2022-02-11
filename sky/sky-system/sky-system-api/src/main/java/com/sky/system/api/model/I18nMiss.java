package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_i18n_miss")
public class I18nMiss extends BaseModel<I18nMiss> {

    @ApiModelProperty(value = "语言类型")
    private String languageType;

    @ApiModelProperty(value = "编码")
    private String code;

}
