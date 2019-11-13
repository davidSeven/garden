package com.stream.garden.framework.web.remote;

/**
 * @author garden
 * @date 2019-11-12 17:50
 */
public interface RemoteAuthorization {

    /**
     * 验证类型
     *
     * @return string
     */
    String getType();

    /**
     * 验证
     *
     * @param remoteAuthorization 标识
     * @param token               token
     * @return boolean
     */
    boolean authorization(String remoteAuthorization, String token);
}
