package com.sky.framework.api.exception;

import com.sky.framework.api.enums.AppCode;

public class CommonException extends RuntimeException {
    private static final long serialVersionUID = -1411235866001927995L;

    private int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(AppCode appCode) {
        this(appCode.getCode(), appCode.getMessage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
