package com.stream.garden.framework.limit.exception;

import com.stream.garden.framework.api.exception.AppCode;

/**
 * @author garden
 * @date 2019-07-20 9:46
 */
public enum LimitExceptionCode implements AppCode {

    /*
     * 15000-15499
     */

    /**
     * 系统繁忙
     */
    SYSTEM_BUSY(15000, "系统繁忙，请稍后再试"),
    SYSTEM_EXCEPTION(15001, "系统异常，请稍后再试"),
    ;

    private int code;
    private String message;

    private LimitExceptionCode(int code, String message) {
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
