package com.stream.garden.framework.web.remote;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author garden
 * @date 2019-11-12 17:52
 */
public class RemoteAuthorizationUtil {

    /**
     * 远程请求标识
     */
    public static final String REMOTE_AUTHORIZATION = "remote-authorization";

    /**
     * 远程请求token
     */
    public static final String REMOTE_AUTHORIZATION_TOKEN = "remote-authorization-token";

    /**
     * 验证是否为远程请求
     *
     * @param request request
     * @return boolean
     */
    public static boolean isRemote(HttpServletRequest request) {
        if (null == request) {
            return false;
        }
        // 从header中进行验证
        return Objects.nonNull(request.getHeader(REMOTE_AUTHORIZATION));
    }


}
