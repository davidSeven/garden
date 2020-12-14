package com.stream.garden.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @date 2020-12-10 010 10:35
 */
@Data
@ApiModel(value = "BasFileInfoUploadDto", description = "BasFileInfoUploadDto信息")
public class BasFileInfoUploadDto implements Serializable {

    @ApiModelProperty(value = "业务ID")
    private String bizId;

    @ApiModelProperty(value = "业务编码")
    private String bizCode;

    @ApiModelProperty(value = "每一项文件的信息")
    private List<BasFileInfoUploadItemDto> itemList;
}
