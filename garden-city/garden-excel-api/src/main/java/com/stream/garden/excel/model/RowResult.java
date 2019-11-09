package com.stream.garden.excel.model;

import lombok.Data;

/**
 * @author garden
 * @date 2019-11-09 10:37
 */
@Data
public class RowResult {
    /**
     * 错误行号
     */
    private int rowIndex;

    /**
     * 错误信息
     */
    private String errorMessage;
}
