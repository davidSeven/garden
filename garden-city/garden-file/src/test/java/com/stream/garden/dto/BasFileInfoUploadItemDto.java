package com.stream.garden.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @date 2020-12-10 010 10:35
 */
@Data
@ApiModel(value = "BasFileInfoUploadItemDto", description = "BasFileInfoUploadItemDto信息")
public class BasFileInfoUploadItemDto implements Serializable {

    @ApiModelProperty(value = "备注")
    private String remark;

}
