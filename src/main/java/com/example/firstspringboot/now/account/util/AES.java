package com.example.firstspringboot.now.account.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author wchun
 * <p>
 * AES128 算法，加密模式为ECB，填充模式为 pkcs7（实际就是pkcs5）
 */
public class AES {

    public static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    public static final String CHAR_ENCODING = "UTF-8";

    /**
     * 加密
     *
     * @param /    data
     *             待加密密内容
     * @param /key 加密密钥
     * @return
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        if (key.length != 16) {
            throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, seckey);// 初始化
            byte[] result = cipher.doFinal(data);
            return result; // 加密
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    public static String encryptAES(String data, String key) throws Exception {
        Key k = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, k);
        return org.apache.commons.codec.binary.Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
    }


    public static String decryptAES(String data, String key) throws Exception {
        Key k = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, k);
        return new String(cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(data)));
    }




    /**
     * 解密
     *
     * @param /         content
     *                  待解密内容
     * @param /password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        if (key.length != 16) {
            throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, seckey);// 初始化
            byte[] result = cipher.doFinal(data);
            return result; // 加密
        } catch (Exception e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    public static String encryptToBase64(String data, String key) {
        try {
            byte[] valueByte = encrypt(data.getBytes(CHAR_ENCODING), key.getBytes(CHAR_ENCODING));

            return Base64.getEncoder().encodeToString(valueByte);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encrypt fail!", e);
        }

    }

    public static String decryptFromBase64(String data, String key) {
        try {
            byte[] originalData = Base64.getDecoder().decode(data);
            byte[] valueByte = decrypt(originalData, key.getBytes(CHAR_ENCODING));
            return new String(valueByte, CHAR_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    public static String encryptWithKeyBase64(String data, String key) {
        try {
            byte[] valueByte = encrypt(data.getBytes(CHAR_ENCODING), Base64.getDecoder().decode(key));
            return Base64.getEncoder().encodeToString(valueByte);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    public static String decryptWithKeyBase64(String data, String key) {
        try {
            byte[] originalData = Base64.getDecoder().decode(data);
            byte[] valueByte = decrypt(originalData, Base64.getDecoder().decode(key));
            return new String(valueByte, CHAR_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    public static byte[] genarateRandomKey() {
        KeyGenerator keygen = null;
        try {
            keygen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(" genarateRandomKey fail!", e);
        }
        SecureRandom random = new SecureRandom();
        keygen.init(random);
        Key key = keygen.generateKey();
        return key.getEncoded();
    }

    public static String genarateRandomKeyWithBase64() {
        return Base64.getEncoder().encodeToString(genarateRandomKey());
    }
}
