package com.stream.garden.framework.api.exception;

import java.text.MessageFormat;

/**
 * @author garden
 */
public interface AppCode {

    /**
     * 获取异常编码
     *
     * @return 编码
     */
    int getCode();

    /**
     * 设置异常编码
     *
     * @param code 编码
     */
    void setCode(int code);

    /**
     * 获取异常消息
     *
     * @return 消息
     */
    String getMessage();

    /**
     * 设置异常消息
     *
     * @param message 消息
     */
    void setMessage(String message);

    /**
     * 验证请求是否成功
     *
     * @return 是否成功
     */
    default boolean isSuccess() {
        return this.getCode() == 0;
    }

    /**
     * 获取异常编码
     *
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

    /**
     * 替换站位参数
     *
     * @param arguments 参数
     * @return 格式化后的消息
     */
    default AppCode format(Object... arguments) {
        this.setMessage(MessageFormat.format(this.getMessage(), arguments));
        return this;
    }
}
