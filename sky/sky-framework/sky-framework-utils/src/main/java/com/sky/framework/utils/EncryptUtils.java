package com.sky.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sky
 * @date 2019-09-27 16:55
 */
public final class EncryptUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);
    private static final String UTF8 = StandardCharsets.UTF_8.toString();
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static final Map<Integer, String> KEY_MAP = new HashMap<>();

    private EncryptUtils() {
    }

    public static String base64Encoder(String src) throws UnsupportedEncodingException {
        return base64Encoder(src.getBytes(UTF8));
    }

    public static String base64Encoder(byte[] encode) {
        return Base64.getEncoder().encodeToString(encode);
    }

    public static String base64Decoder(String dest) throws UnsupportedEncodingException {
        return new String(base64DecoderByte(dest), UTF8);
    }

    public static byte[] base64DecoderByte(String dest) {
        return Base64.getDecoder().decode(dest);
    }

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
            byte[] byteContent = content.getBytes(UTF8);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return base64Encoder(result);//通过Base64转码返回
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content  content
     * @param password password
     * @return String
     */
    public static String decrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 实例化
            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            // 执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(result, UTF8);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return SecretKeySpec
     */
    private static SecretKeySpec getSecretKey(final String password) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));
            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException e
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = base64Encoder(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = base64Encoder(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        KEY_MAP.put(0, publicKeyString);  //0表示公钥
        KEY_MAP.put(1, privateKeyString);  //1表示私钥
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encryptRSA(String str, String publicKey) throws Exception {
        // base64编码的公钥
        byte[] decoded = base64DecoderByte(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return base64Encoder(cipher.doFinal(str.getBytes(UTF8)));
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decryptRSA(String str, String privateKey) throws Exception {
        // 64位解码加密后的字符串
        byte[] inputByte = base64DecoderByte(str);
        // base64编码的私钥
        byte[] decoded = base64DecoderByte(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    public static String readPrivateKey(String path) {
        // /rsakey/privatekey
        InputStream inputStream = EncryptUtils.class.getResourceAsStream(path);
        StringBuilder builder = new StringBuilder();
        byte[] bytes = new byte[128];
        try {
            int i;
            while ((i = inputStream.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, i, UTF8));
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return builder.toString().replaceAll("[\r\n]", "");
    }

    private static String wordwrap(String content, int lineLength) {
        int contentLength = content.length();
        int lineSize = contentLength / lineLength;
        if (contentLength % lineLength != 0) {
            lineSize = lineSize + 1;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lineSize; i++) {
            if (i + 1 == lineSize) {
                builder.append(content.substring(lineLength * (i)));
            } else {
                builder.append(content, lineLength * (i), lineLength * (i + 1));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private static String unWordwrap(String content) {
        return content.replaceAll("[\n]", "");
    }

    public static void main(String[] args) {
        try {
            // 生成公钥和私钥
            genKeyPair();
            // 加密字符串
            String message = "123456";

            int lineLength = 64;
            String publicKey = wordwrap(KEY_MAP.get(0), lineLength);
            String privateKey = wordwrap(KEY_MAP.get(1), lineLength);

            logger.info("随机生成的公钥为: \n{}", publicKey);
            logger.info("随机生成的私钥为:\n{}", privateKey);
            System.out.println(privateKey.length());
            logger.info("随机生成的私钥为:\n{}", unWordwrap(privateKey));
            System.out.println(unWordwrap(privateKey).length());
            String messageEn = encryptRSA(message, KEY_MAP.get(0));
            logger.info("加密前的字符串为:{}\n加密后的字符串为:{}", message, messageEn);
            logger.info("私钥长度:{}", KEY_MAP.get(1).length());
            logger.info("私钥长度:{}", unWordwrap(KEY_MAP.get(1)).length());
            String messageDe = decryptRSA(messageEn, unWordwrap(privateKey));
            logger.info("还原后的字符串为:{}", messageDe);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
