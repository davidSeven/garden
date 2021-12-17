package com.sky.framework.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @date 2020-10-28 028 13:53
 */
public abstract class AbstractQueryDto {

    @ApiModelProperty(value = "当前页，从1开始，默认为1", example = "1")
    private int pageNum = 1;

    @ApiModelProperty(value = "每页的数量，默认为15", example = "15")
    private int pageSize = 15;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
