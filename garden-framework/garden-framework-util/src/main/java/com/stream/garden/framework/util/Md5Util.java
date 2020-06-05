package com.stream.garden.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * MD5加密
 *
 * @author garden
 */
@SuppressWarnings("unused")
public final class Md5Util {
    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    private static final String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private Md5Util() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes(StandardCharsets.UTF_8));
            return algorithm.digest();
        } catch (Exception e) {
            logger.error("MD5 Error", e);
        }
        return null;
    }

    private static String toHex(byte[] hash) {
        if (hash == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(hash.length * 2);
        int i;
        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            String hex = toHex(md5(s));
            assert hex != null;
            byte[] bytes = hex.getBytes(StandardCharsets.UTF_8);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return s;
        }
    }

    /**
     * 对密码按照用户名，密码，盐进行加密
     *
     * @param username 用户名
     * @param password 密码
     * @param salt     盐
     * @return String
     */
    @Deprecated
    public static String encryptPassword(String username, String password, String salt) {
        return Md5Util.hash(username + password + salt);
    }

    /**
     * 对密码按照密码，盐进行加密
     *
     * @param password 密码
     * @param salt     盐
     * @return String
     */
    @Deprecated
    public static String encryptPassword(String password, String salt) {
        return Md5Util.hash(password + salt);
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // MD5加密
    public static String encoderByMD5(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : messageDigest.digest(string.getBytes())) {
                stringBuilder.append(byteToArrayString(b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 判断用户名密码是否正确
     *
     * @deprecated newPassword加密处理了，而oldPassword没有加密处理
     */
    @Deprecated
    public static boolean checkPassword(String newPassword, String oldPassword) {
        return Objects.equals(encoderByMD5(newPassword), oldPassword);
    }

}
