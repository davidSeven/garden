package com.stream.garden.framework.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public final class DESUtil {
    private static final Logger logger = LoggerFactory.getLogger(DESUtil.class);

    private static final String UTF8 = StandardCharsets.UTF_8.toString();
    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param code String
     * @param key  String
     * @return String
     */
    public static String encrypt(String code, String key) {
        try {
            return Base64.encodeBase64String(encrypt(code.getBytes(UTF8), key.getBytes(UTF8)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 加密
     *
     * @param code byte[]
     * @param key  byte[]
     * @return byte[]
     * @throws Exception 异常
     */
    public static byte[] encrypt(byte[] code, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        // 生成密钥
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        // 进行加密
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        return cipher.doFinal(code);
    }

    /**
     * 解密
     *
     * @param code String
     * @param key  String
     * @return String
     */
    public static String decrypt(String code, String key) {
        try {
            return new String(decrypt(Base64.decodeBase64(code), key.getBytes(UTF8)), UTF8);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    /**
     * 解密
     *
     * @param src byte[]
     * @param key byte[]
     * @return byte[]
     * @throws Exception 异常
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        // 生成密钥
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey sectetKey = keyFactory.generateSecret(dks);
        // 进行加密
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.DECRYPT_MODE, sectetKey, sr);
        return cipher.doFinal(src);
    }

}
