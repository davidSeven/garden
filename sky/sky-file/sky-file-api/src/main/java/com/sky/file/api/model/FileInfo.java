package com.sky.file.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_file_info")
public class FileInfo extends BaseModel<FileInfo> {

    @ApiModelProperty(value = "业务ID")
    private String bizId;

    @ApiModelProperty(value = "业务编码")
    private String bizCode;

    @ApiModelProperty(value = "原文件名称")
    private String originalName;

    @ApiModelProperty(value = "扩展名")
    private String extendName;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "物理存储路径")
    private String physicalPath;

    @ApiModelProperty(value = "访问路径")
    private String visitPath;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "文件头类型")
    private String contentType;

    @TableField(exist = false)
    @ApiModelProperty(value = "文件流")
    private byte[] bytes;
}
