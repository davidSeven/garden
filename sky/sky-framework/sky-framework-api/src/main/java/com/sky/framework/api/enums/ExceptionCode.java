package com.sky.framework.api.enums;

public enum ExceptionCode implements AppCode {

    UNKNOWN_EXCEPTION(-1, "请求失败"),
    SERVICE_EXCEPTION(-2, "服务{}不可用"),
    SUCCESS(0, "success"),
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

    @Override
    public void refreshFormat(Object[] arguments) {
        if (!message.contains("{}")) {
            return;
        }
        if (null == arguments) {
            message = message.replaceAll("\\{}", "");
        } else {
            for (Object arg : arguments) {
                if (message.contains("{}")) {
                    message = message.replaceFirst("\\{}", String.valueOf(arg));
                }
            }
        }
    }
}
