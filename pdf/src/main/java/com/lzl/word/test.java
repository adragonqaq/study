package com.lzl.word;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

public class test {
    public static void main(String[] args) {
        String s = encrypt32("Abc1234");
        System.out.println(s);
        String s1 = DigestUtils.md5Hex("Abc1234");
        System.out.println(s1);
    }


    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr.toUpperCase();
    }
}
