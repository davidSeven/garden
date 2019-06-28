package com.stream.garden.framework.api.exception;

/**
 * @author garden
 */
public interface AppCode {

    /**
     * 获取异常编码
     * @return 编码
     */
    int getCode();

    /**
     * 设置异常编码
     * @param code 编码
     */
    void setCode(int code);

    /**
     * 获取异常消息
     * @return 消息
     */
    String getMessage();

    /**
     * 设置异常消息
     * @param message 消息
     */
    void setMessage(String message);

    /**
     * 验证请求是否成功
     * @return 是否成功
     */
    default boolean isSuccess() {
        return this.getCode() == 0;
    }

    /**
     * 获取异常编码
     * @param e 异常
     * @return 异常编码
     */
    default AppCode getAppCode(Exception e) {
        if (e instanceof ApplicationException) {
            ApplicationException ae = (ApplicationException) e;
            if (null != ae.getAppCode()) {
                return ae.getAppCode();
            }
        }
        return this;
    }
}
