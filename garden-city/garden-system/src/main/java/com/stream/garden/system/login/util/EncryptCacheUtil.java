package com.stream.garden.system.login.util;

import com.stream.garden.framework.util.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 加密缓存工具类
 *
 * @author garden
 * @date 2019-09-28 8:53
 */
public class EncryptCacheUtil {
    private static Logger logger = LoggerFactory.getLogger(EncryptCacheUtil.class);

    /**
     * 缓存map
     */
    private static Map<String, String> CACHE_MAP = new HashMap<>();

    /**
     * 登录密码rsa私钥
     */
    private static String LOGIN_PASSWORD_RSA_PRIVATE_KEY = "LOGIN_PASSWORD_RSA_PRIVATE_KEY";

    /**
     * 登录密码rsa公钥
     */
    private static String LOGIN_PASSWORD_RSA_PUBLIC_KEY = "LOGIN_PASSWORD_RSA_PUBLIC_KEY";

    /**
     * 获取rsa私钥
     *
     * @return String
     */
    public static String getRsaPrivateKey() {
        // 判断私钥是否在缓存中
        if (!CACHE_MAP.containsKey(LOGIN_PASSWORD_RSA_PRIVATE_KEY)) {
            reloadRsaPrivateKey();
        }
        return CACHE_MAP.get(LOGIN_PASSWORD_RSA_PRIVATE_KEY);
    }

    /**
     * 重新加载rsa私钥
     */
    private static void reloadRsaPrivateKey() {
        // 加载私钥文件
        String privateKey = EncryptUtils.readPrivateKey("/rsakey/privateKey.rsa");
        // 放到缓存中
        CACHE_MAP.put(LOGIN_PASSWORD_RSA_PRIVATE_KEY, privateKey);
    }

    /**
     * 解析rsa加密密码
     *
     * @param rsaPassword rsa密码
     * @return String
     */
    public static String decryptRsaPassword(String rsaPassword) {
        try {
            return EncryptUtils.decryptRSA(rsaPassword, getRsaPrivateKey());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 解析失败，返回原密码
            return rsaPassword;
        }
    }
}
