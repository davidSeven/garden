package com.stream.garden.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * 文件管理
 * </p>
 *
 * @author gen
 * @since 2020-12-08
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "文件管理", description = "BasFileInfo对象")
public class BasFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @ApiModelProperty(value = "属性1")
    private String attribute1;

    @ApiModelProperty(value = "属性2")
    private String attribute2;

    @ApiModelProperty(value = "属性3")
    private String attribute3;

    @ApiModelProperty(value = "属性4")
    private String attribute4;

    @ApiModelProperty(value = "属性5")
    private String attribute5;

    @ApiModelProperty(value = "属性6")
    private String attribute6;

    @ApiModelProperty(value = "备注")
    private String remark;

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
    private byte[] bytes;
}
