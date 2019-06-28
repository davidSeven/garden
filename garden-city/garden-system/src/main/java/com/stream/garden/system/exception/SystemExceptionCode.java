package com.stream.garden.system.exception;

import com.stream.garden.framework.api.exception.AppCode;
import com.stream.garden.framework.api.exception.ApplicationException;

/**
 * @author garden
 * @date 2019-06-21 10:31
 */
public enum SystemExceptionCode implements AppCode {

    /*
     * 20000-29999
     * USER     20000 - 202999
     * ROLE     20300 - 205999
     * GROUP    20600 - 208999
     * MENU     20900 - 311999
     */

    /** 用户新增失败 */
    USER_INSERT_EXCEPTION(20000, "insert user exception"),
    /** 用户编码重复 */
    USER_CODE_REPEAT(20001, "insert user code repeat"),

    MENU_ADD_EXCEPTION(20900, "add menu exception"),
    MENU_NAME_REPEAT(20901, "add menu name repeat"),
    MENU_EDIT_EXCEPTION(20902, "edit menu exception"),
    MENU_DELETE_EXCEPTION(20903, "exits children node"),
    ;

    private int code;
    private String message;

    private SystemExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

}
