package com.stream.garden.excel.model;

import lombok.Data;

import java.util.List;

/**
 * @author garden
 * @date 2019-11-09 10:36
 */
@Data
public class ExcelParseResult {

    /**
     * 验证结果
     */
    private boolean status;

    /**
     * 解析的行数
     */
    private int rowNum;

    /**
     * 错误信息
     */
    private List<RowResult> rowResults;
}
