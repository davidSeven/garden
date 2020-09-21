package com.stream.forest.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AES加解密算法
 * <p>
 * AesUtil
 */
public class AesUtil {
    /**
     * encrypt
     *
     * @param sSrc sSrc
     * @param sKey sKey
     * @return String
     * @throws Exception Exception
     */
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (StringUtils.isBlank(sKey)) {
            throw new RuntimeException("decrypt source key can not be empty.");
        }
        if (sKey.length() != 16) {
            throw new RuntimeException("decrypt source key's length must be 16.");
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec(sKey.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return Base64.encodeBase64String(encrypted);// 此处使用BAES64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * decrypt
     *
     * @param sSrc sSrc
     * @param sKey sKey
     * @return String
     * @throws Exception Exception
     */
    public static String decrypt(String sSrc, String sKey) throws Exception {
        if (StringUtils.isBlank(sKey)) {
            throw new RuntimeException("decrypt source key can not be empty.");
        }
        if (sKey.length() != 16) {
            throw new RuntimeException("decrypt source key's length must be 16.");
        }
        byte[] raw = sKey.getBytes(StandardCharsets.US_ASCII);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(sKey.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 = Base64.decodeBase64(sSrc);// 先用bAES64解密
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original);
    }
}
