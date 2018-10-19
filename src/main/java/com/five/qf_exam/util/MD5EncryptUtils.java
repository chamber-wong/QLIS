package com.five.qf_exam.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @description MD5加密数据的工具类
 * @author: Cwx 810249001@qq.com
 * @create: 2018-10-18 20:37:10
 **/

public class MD5EncryptUtils {

    /**
     *MD5数据加密方法（两个参数）
     * @param plaintext  明文
     * @param secretkey   密钥
     * @return  密文
     * @throws Exception
     */
    public static String encryptMD5(String plaintext, String secretkey) throws Exception {
        //加密后的字符串
        String ciphertext= DigestUtils.md5Hex(plaintext + secretkey);
        //System.out.println("MD5加密后的字符串为:ciphertext="+ciphertext);
        return ciphertext;
    }

    /**
     * MD5加密方法（一个参数） 此处密钥写死（默认为QLIS）
     * @param  plaintext 明文
     * @return  密文
     * @throws Exception
     */
    public static String encryptMD5(String plaintext) throws Exception {
        //加密后的字符串
        String ciphertext= DigestUtils.md5Hex(plaintext + "QLIS");
        //System.out.println("MD5加密后的字符串为:ciphertext="+ciphertext);
        return ciphertext;
    }

    /**
     *
     * @param plaintext  明文
     * @param secretkey   密钥
     * @param ciphertext   密文
     * @return  true/false
     * @throws Exception
     */
    public static boolean verify(String plaintext, String secretkey, String ciphertext) throws Exception {
        //根据传入的密钥进行验证
        String encodeStr = encryptMD5(plaintext, secretkey);
        if(encodeStr.equalsIgnoreCase(ciphertext))
        {
            System.out.println("MD5加密验证通过");
            return true;
        }
        System.out.println("MD5加密验证失败！");
        return false;
    }
}
