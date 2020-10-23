package com.sky.framework.api.enums;

public interface AppCode {

    int getCode();

    void setCode(int code);

    String getMessage();

    void setMessage(String message);

    default boolean isSuccess() {
        return this.getCode() == 0;
    }
}
