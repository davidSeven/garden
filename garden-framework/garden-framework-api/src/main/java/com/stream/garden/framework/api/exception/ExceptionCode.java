package com.stream.garden.framework.api.exception;

/**
 * @author garden
 */

public enum ExceptionCode implements AppCode {

    /** 请求失败 */
    UNKOWN_EXCEPTION(-1, "请求失败"),
    /** success */
    SUCCESS(0, "success"),
    TIME_OUT(408, "请求超时"),

    ADD_EXCEPTION(500, "新增异常"),
    EDIT_EXCEPTION(500, "修改异常"),

    ;

    private int code;
    private String message;

    private ExceptionCode(int code, String message) {
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
