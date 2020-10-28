package com.sky.framework.api.exception;

import com.sky.framework.api.enums.AppCode;

public class CommonException extends RuntimeException {
    private static final long serialVersionUID = -1411235866001927995L;

    private int code;
    private transient Object[] values;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(int code, String message, Object[] values) {
        super(message);
        this.code = code;
        this.values = values;
    }

    public CommonException(AppCode appCode) {
        this(appCode.getCode(), appCode.getMessage());
    }

    public CommonException(AppCode appCode, Object[] values) {
        this(appCode.getCode(), appCode.getMessage(), values);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
