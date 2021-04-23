package com.example.firstspringboot.now.account.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Rsa工具
 *
 * @author adm
 */
public class RsaUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RsaUtil.class);

    private RsaUtil() {
    }

    /**
     * 加密算法RSA
     */
    private static final String RSA_ALGORITHM = "RSA";

    /**
     * 加密模式
     */
    private static final String RSA_CIPTHER = "RSA/ECB/PKCS1Padding";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    /**
     * 初始化KeyPairGenerator对象,密钥长度
     */
    private static final int KEY_SIZE = 1024;







    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
            throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     *
     * @param path 私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public static String loadPrivateKeyByFile(String path) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(path
                + "/privateKey.keystore"))) {
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
            throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 公钥加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(RSA_CIPTHER);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(plainTextData);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            LOGGER.error("异常", e);
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }
    /**
     * 公钥加密过程
     *
     * @param  publicKey    公钥
     * @param  content 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */

    public static String encryptRSA(String content, String publicKey, String inputCharset) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] encodedKey = org.apache.commons.codec.binary.Base64.decodeBase64(publicKey);
        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, pubKey);
        InputStream ins = new ByteArrayInputStream(content.getBytes(inputCharset));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();

        int bufl;
        byte[] block;
        for(byte[] buf = new byte[117]; (bufl = ins.read(buf)) != -1; writer.write(cipher.doFinal(block))) {
            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                System.arraycopy(buf, 0, block, 0, bufl);
            }
        }

        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(writer.toByteArray()), inputCharset);
    }




    /**
     * 私钥加密过程
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData)
            throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(RSA_CIPTHER);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(plainTextData);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            LOGGER.error("异常", e);
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
            throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {

            cipher = Cipher.getInstance(RSA_CIPTHER);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(cipherData);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            LOGGER.error("异常", e);
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 公钥解密过程
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(RSA_CIPTHER);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(cipherData);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            LOGGER.error("异常", e);
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }


    /**
     * 签名（经过Base64编码）
     *
     * @param priKey
     * @param data
     * @return
     */
    public static String sign(String priKey, String data) throws Exception {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(priKey));
            KeyFactory fac = KeyFactory.getInstance("RSA");
            RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
            Signature sigEng = Signature.getInstance(SIGNATURE_ALGORITHM);
            sigEng.initSign(privateKey);
            sigEng.update(data.getBytes());
            byte[] signature = sigEng.sign();
            return Base64.getEncoder().encodeToString(signature);
        } catch (Exception e) {
            LOGGER.error("SHA256withRSA签名异常", e);
            throw e;
        }
    }

    /**
     * 验证签名
     *
     * @param pubKey
     * @param signData
     * @param data
     * @return
     */
    public static boolean verifySign(String pubKey, String signData, String data) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(pubKey));
            KeyFactory fac = KeyFactory.getInstance("RSA");
            RSAPublicKey rsaPubKey = (RSAPublicKey) fac.generatePublic(keySpec);
            Signature sigEng = Signature.getInstance(SIGNATURE_ALGORITHM);
            sigEng.initVerify(rsaPubKey);
            sigEng.update(data.getBytes());
            return sigEng.verify(Base64.getDecoder().decode(signData));
        } catch (Exception e) {
            LOGGER.error("校验收到请求的签名时，发生异常，errorMsg:{}  Exception:{}", e.getMessage(), e);
            return false;
        }
    }

}
