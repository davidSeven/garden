package com.stream.garden.framework.web.remote;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.framework.util.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger logger = LoggerFactory.getLogger(RemoteAuthorizationUtil.class);

    /**
     * 验证是否为远程请求
     *
     * @param request request
     * @return boolean
     */
    public static boolean isRemote(HttpServletRequest request) {
        // 从header中进行验证
        return Objects.nonNull(getRemoteAuthorization(request));
    }

    /**
     * 获取remote authorization
     *
     * @param request request
     * @return string
     */
    public static String getRemoteAuthorization(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        return request.getHeader(REMOTE_AUTHORIZATION);
    }

    /**
     * 请求验证
     *
     * @param request request
     * @return boolean
     */
    public static boolean authorization(HttpServletRequest request) {
        String remoteAuthorization = getRemoteAuthorization(request);
        if (StringUtils.isEmpty(remoteAuthorization)) {
            return false;
        }
        String remoteAuthorizationToken = request.getHeader(REMOTE_AUTHORIZATION_TOKEN);
        if (StringUtils.isEmpty(remoteAuthorizationToken)) {
            return false;
        }
        try {
            // 解析token里面的time字段
            String tokenJson = EncryptUtils.base64Decoder(remoteAuthorizationToken);
            if (StringUtils.isEmpty(tokenJson)) {
                return false;
            }
            JSONObject jsonObject = JSONObject.parseObject(tokenJson);
            if (null == jsonObject || !jsonObject.containsKey("time")) {
                return false;
            }
            long time = jsonObject.getLong("time");
            // 不得超过2分钟
            if (System.currentTimeMillis() - time > 12000) {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
