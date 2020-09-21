package com.stream.forest.framework.common.exception;

import com.stream.forest.framework.common.enums.BaseEnum;

public class CommonException extends BaseException {

    private static final long serialVersionUID = -4527567935254966321L;

    public CommonException(String code) {
        super(code, null, null, null);
    }

    public CommonException(String code, String message) {
        super(code, message, null, null);
    }

    public CommonException(String code, String message, Object[] values) {
        super(code, message, null, values);
    }

    public CommonException(String code, String message, Throwable throwable, Object[] values) {
        super(code, message, throwable, values);
    }

    public CommonException(BaseEnum error) {
        super(error.getKey(), error.getValue(), null, null);
    }

}
