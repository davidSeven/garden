package com.stream.garden.excel.exception;

import com.stream.garden.framework.api.exception.AppCode;

/**
 * @author garden
 * @date 2019-11-04 16:19
 */
public enum ExcelExceptionCode implements AppCode {

    /*
     * 20000-29999
     * USER         20000 - 20999 (1000)
     * ROLE         21000 - 21999 (1000)
     * GROUP        22000 - 22999 (1000)
     * MENU         23000 - 23999 (1000)
     * FUNCTION     24000 - 24999 (1000)
     * LOOKUP       25000 - 25499 (500)
     * LOOKUP_ITEM  25500 - 25999 (500)
     * ORGANIZATION 26000 - 26999 (1000)
     * DATA_SCOPE   27000 - 27999 (1000)
     * POSITION     28000 - 28999 (1000)
     * FILE         29000 - 29499 (500)
     * JOB          29500 - 29999 (500)
     * DICTIONARY   30000 - 30499 (500)
     * I18N         30500 - 30999 (500)
     * CONTENT      31000 - 31499 (500)
     * EXCEL        31500 - 31999 (500)
     */

    /*---------------------------EXCEL------------------------------*/
    EXCEL_CONFIG_ADD_EXCEPTION(31500, "新增异常"),
    EXCEL_CONFIG_EDIT_EXCEPTION(31501, "修改异常"),
    EXCEL_CONFIG_CODE_REPEAT(31502, "编码[{0}]已存在"),

    /*---------------------------EXCEL------------------------------*/;

    private int code;
    private String message;

    private ExcelExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
