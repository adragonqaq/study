package com.lzl.jvm;

/**
 * @author eren.liao
 * @date 2023/4/6 15:13
 */
public class test {



    public static void main(String[] args) {
        String encrypt = EncryptUtils.encrypt("694203426891878400710", "1234567890123456","1234567890123456");
        System.out.println(encrypt);

        String desEncrypt = EncryptUtils.desEncrypt("vk1KKtzjVA3Knhyo2CU2oVoDoxipAIne4Bw4kH6f+io=", "1234567890123456","1234567890123456");

        System.out.println(desEncrypt);


    }
}
