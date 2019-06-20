package com.stream.garden.framework.api.model;

/**
 * @author garden
 */

public enum ExceptionCode implements AppCode {

    /** 请求失败 */
    UNKOWN_EXCEPTION(-1, "请求失败"),
    /** success */
    SUCCESS(0, "success")
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
