package com.sky.system.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "I18nMissQueryDto", description = "I18nMissQueryDto信息")
public class I18nMissQueryDto extends AbstractQueryDto {

    @ApiModelProperty(value = "语言类型")
    private String languageType;

    @ApiModelProperty(value = "编码")
    private String code;
}
