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
    private static Map<String, String> cacheMap = new HashMap<>();
    /**
     * 登录密码rsa私钥
     */
    private static String loginRsaPrivateKey = "loginRsaPrivateKey";
    /**
     * 登录密码rsa公钥
     */
    private static String loginRsaPublicKey = "loginRsaPublicKey";

    private EncryptCacheUtil() {
    }

    /**
     * 获取rsa私钥
     *
     * @return String
     */
    public static String getRsaPrivateKey() {
        // 判断私钥是否在缓存中
        if (!cacheMap.containsKey(loginRsaPrivateKey)) {
            reloadRsaPrivateKey();
        }
        return cacheMap.get(loginRsaPrivateKey);
    }

    /**
     * 获取rsa公钥
     *
     * @return string
     */
    public static String getRsaPublicKey() {
        // 判断公钥是否在缓存中
        if (!cacheMap.containsKey(loginRsaPublicKey)) {
            reloadRsaPublicKey();
        }
        return cacheMap.get(loginRsaPublicKey);
    }

    /**
     * 重新加载rsa私钥
     */
    private static void reloadRsaPrivateKey() {
        reloadRsaKey(loginRsaPrivateKey, "/rsakey/privateKey.rsa");
    }

    /**
     * 重新加载rsa公钥
     */
    private static void reloadRsaPublicKey() {
        reloadRsaKey(loginRsaPublicKey, "/rsakey/publicKey.rsa");
    }

    /**
     * 重新加载rsa
     *
     * @param key     key
     * @param keyPath key地址
     */
    private static void reloadRsaKey(String key, String keyPath) {
        // 加载私钥文件
        String privateKey = EncryptUtils.readPrivateKey(keyPath);
        // 放到缓存中
        cacheMap.put(key, privateKey);
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
