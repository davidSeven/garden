package com.stream.garden.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @date 2020-12-08 008 11:40
 */
@ApiModel(value = "FileParameterDto", description = "文件信息")
public class FileParameterDto implements Serializable {

    @ApiModelProperty(value = "上传文件数量")
    private int num;

    @ApiModelProperty(value = "上传的文件")
    private List<BasFileInfo> files;

    @ApiModelProperty(value = "是否压缩")
    private boolean compressionEnabled;

    @ApiModelProperty(value = "BizId")
    private String bizId;

    @ApiModelProperty(value = "BizCode")
    private String bizCode;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<BasFileInfo> getFiles() {
        return files;
    }

    public void setFiles(List<BasFileInfo> files) {
        this.files = files;
    }

    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    public void setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

}
