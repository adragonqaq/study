package com.lzl.jvm;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/**
 * AES 加密及解密
 * @author sambor.tan
 * @date 2018/7/24 18:59
 */
public class EncryptUtils {

    private EncryptUtils(){}



    private static final String AES_NAME = "AES";

    private static final String UN_KNOWN = "unknown";

    // 加密模式-PKCS7
    public static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 默认key和iv
     */
    public static final String KEY = "1234567890123456";
    public static final String IV = "1234567890123456";

    /**
     * emailKey
     */
    private static final String EMAIL_KEY = "email12345678901";

    /**
     * tokenKey
     */
    private static final String TOKEN_KEY = "token12345678901";

    /**
     * smsKey
     */
    private static final String SMS_KEY = "sms1234567890123";

    /**
     * 储值卡支付key和iv
     */
    public static final String SVC_PAY_KEY = "OYldX6Z9rnV2pBLk";
    public static final String SVC_PAY_IV = "4870281381529301";

    /**
     * AES key
     */
    private static final String AES_KEY = "209386C39DC47374C39F1A145F490F0A";

    /**
     * 随机 key
     */
    public static final String RANDOM_KEY = "Y3MnOCGtLO0OE3gx";

    /**
     * 系统参数加密 key和iv
     */
    public static final String SYSTEM_PARAM_KEY = "ZxJeOam9mK9Rb99E";
    public static final String SYSTEM_PARAM_IV = "4268468152541379";

    /**
     * 加密方法
     * @param data  要加密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 加密的结果
     */
    public static String encrypt(String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new Base64().encodeToString(encrypted);

        } catch (Exception e) {
            System.out.println("异常"+e.getMessage());
            return null;
        }
    }




    /**
     * 解密方法
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv 解密iv
     * @return 解密的结果
     */
    public static String desEncrypt(String data, String key, String iv) {
        try {
            byte[] encrypted1 = new Base64().decode(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString.trim();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * AES-PKCS7补码加密
     * @param content 待加密文本
     * @return String
     */
    public static String encryptPKCS7(String content) {
        byte[] result;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            result = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            return null;
        }
        return Base64.encodeBase64String(result);
    }

    /**
     * AES-PKCS7补码解密
     * @param content 待解密文本
     * @return String
     */
    public static String decryptPKCS7(String content) {
        String result;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            result = new String(cipher.doFinal(Base64.decodeBase64(content)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
        return result;
    }



    /**
     * 获取加密key
     * @return String
     */
    private static String getKey(){
        return RandomStringUtils.random(16,true,true);
    }

    /**
     * 获取加密邮件key
     * @return String
     */
    public static String getEmailKey() {
        return EMAIL_KEY;
    }



    /**
     * 获取加密Token的key
     * @return String
     */
    public static String getTokenKey() {
        return TOKEN_KEY;
    }


    /**
     * 获取加密Sms的key
     * @return String
     */
    public static String getSmsKey() {
        return SMS_KEY;
    }



    /**
     * AES 加密
     * @param text 明文
     * @param key 加密key
     * @return String
     */
    public static String encodeAES(String text,String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        if (StringUtils.isBlank(text)) {
            return null;
        }

        // 配置--加密与解密公用的
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = generator.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        byte[] byteContent = text.getBytes("utf-8");

        // 初始化加密器
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] bytes = cipher.doFinal(byteContent);

        // 不可直接转成字符串
        if (null == bytes || bytes.length < 1) {
            return null;
        }
        return parseByte2HexStr(bytes);

    }


    /**
     * AES 解密
     * @param cryptText 密文
     * @param key 加密key
     * @return String
     */
    public static String decodeAES(String cryptText, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if (StringUtils.isBlank(cryptText)) {
            return null;
        }

        // 配置--加密与解密公用的
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = generator.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");

        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        // 初始化解密器
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        // 进制转换
        byte[] decryptFrom = parseHexStr2Byte(cryptText);
        if (decryptFrom.length < 1) {
            return null;
        }

        // 解密
        byte[] bytes = cipher.doFinal(decryptFrom);
        if (null == bytes || bytes.length < 1) {
            return null;
        }

        return new String(bytes);
    }

    /**
     * 二进制转换成16进制
     * @param buf 二进制byte数组
     * @return String
     */
    private static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制转换成二进制
     * @param hexStr 需要转换的字符串
     * @return byte[]
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return new byte[hexStr.length() / 2];
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**
     * 字符串转换成为16进制(无需Unicode编码)
     * @param str 字符串
     * @return String
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        int bit;
        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr 16进制字符串
     * @return String
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] chars = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(chars[2 * i]) * 16;
            n += str.indexOf(chars[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }




}
