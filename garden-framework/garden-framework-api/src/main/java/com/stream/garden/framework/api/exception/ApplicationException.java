package com.stream.garden.framework.api.exception;

/**
 * @author garden
 * @date 2019-06-21 10:16
 */
public class ApplicationException extends Exception {
    private static final long serialVersionUID = 6305472432182539635L;

    private AppCode appCode;

    public ApplicationException() {
        super();
    }

    public ApplicationException(AppCode appCode) {
        super(appCode.getMessage());
        this.appCode = appCode;
    }

    public ApplicationException(Throwable cause, AppCode appCode) {
        super(appCode.getMessage(), cause);
        this.appCode = appCode;
    }

    public AppCode getAppCode() {
        return appCode;
    }

    public void setAppCode(AppCode appCode) {
        this.appCode = appCode;
    }
}
