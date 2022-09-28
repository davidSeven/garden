package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_serial_number")
public class SerialNumber extends BaseModel<SerialNumber> {

    @ApiModelProperty(value = "业务编码")
    private String code;

    @ApiModelProperty(value = "当前序列")
    private Long currentSequence;

    @ApiModelProperty(value = "步长")
    private Integer step;

    @ApiModelProperty(value = "流水号长度")
    private Integer length;

    @ApiModelProperty(value = "前缀")
    private String prefix;

    @ApiModelProperty(value = "流水号类型")
    private Integer type;

    @ApiModelProperty(value = "模板")
    private String template;

    @ApiModelProperty(value = "周期格式")
    private String cycleFormat;

    @ApiModelProperty(value = "当前周期")
    private String currentCycle;

    @ApiModelProperty(value = "缓存数量")
    private Integer cacheSize;

    @ApiModelProperty(value = "转换器")
    private String converts;
}
