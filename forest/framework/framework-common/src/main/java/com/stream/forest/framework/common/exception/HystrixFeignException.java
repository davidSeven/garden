package com.stream.forest.framework.common.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

@SuppressWarnings("unused")
public class HystrixFeignException extends HystrixBadRequestException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private Throwable throwable;
    private transient Object[] values;

    public HystrixFeignException() {
        super(null);
    }

    public HystrixFeignException(String code) {
        super(null);
        this.code = code;
    }

    public HystrixFeignException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public HystrixFeignException(String code, String message, Object[] values) {
        super(message);
        this.code = code;
        this.message = message;
        this.values = values;
    }

    public HystrixFeignException(String code, String message, Throwable throwable, Object[] values) {
        super(message);
        this.code = code;
        this.message = message;
        this.throwable = throwable;
        this.values = values;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
