package com.sky.file.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.dao.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2020-12-14 014 19:57
 */
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

    /**
     * 文件流
     */
    @ApiModelProperty(value = "文件流")
    @TableField(exist = false)
    private byte[] bytes;
}
