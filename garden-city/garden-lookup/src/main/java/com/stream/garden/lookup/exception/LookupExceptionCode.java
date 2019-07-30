package com.stream.garden.lookup.exception;

import com.stream.garden.framework.api.exception.AppCode;

/**
 * @author garden
 * @date 2019-06-21 10:31
 */
public enum LookupExceptionCode implements AppCode {

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
     */

    /*---------------------------LOOKUP------------------------------*/
    LOOKUP_ADD_EXCEPTION(25000, "新增Lookup异常"),
    LOOKUP_EDIT_EXCEPTION(25001, "修改Lookup异常"),

    LOOKUP_ITEM_ADD_EXCEPTION(25500, "新增Lookup子项异常"),
    LOOKUP_ITEM_EDIT_EXCEPTION(25501, "修改Lookup子项异常"),
    /*---------------------------LOOKUP------------------------------*/

    ;

    private int code;
    private String message;

    private LookupExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
