package com.stream.garden.framework.api.exception;

import java.text.MessageFormat;

/**
 * @author garden
 * @date 2019-06-21 10:16
 */
public class ApplicationException extends Exception {
    private static final long serialVersionUID = 6305472432182539635L;

    private AppCode appCode;
    private Object[] arguments;

    public ApplicationException() {
        super();
    }

    public ApplicationException(AppCode appCode) {
        super(appCode.getMessage());
        this.appCode = appCode;
    }

    public ApplicationException(AppCode appCode, Object... arguments) {
        super(MessageFormat.format(appCode.getMessage(), arguments));
        this.appCode = appCode;
        this.arguments = arguments;
    }

    public ApplicationException(Throwable cause, AppCode appCode) {
        super(appCode.getMessage(), cause);
        this.appCode = appCode;
        if (cause instanceof ApplicationException) {
            this.setAppCode(((ApplicationException) cause).getAppCode());
        }
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
        if (cause instanceof ApplicationException) {
            ApplicationException ae = (ApplicationException) cause;
            this.setAppCode(ae.getAppCode());
            this.setArguments(ae.getArguments());
        }
    }

    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        if (cause instanceof ApplicationException) {
            this.setAppCode(((ApplicationException) cause).getAppCode());
        }
    }

    public AppCode getAppCode() {
        return appCode;
    }

    public void setAppCode(AppCode appCode) {
        this.appCode = appCode;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
