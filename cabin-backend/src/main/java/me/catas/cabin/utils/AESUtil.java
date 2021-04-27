package me.catas.cabin.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author Calisto
 * @date 2021/4/24 1:24 下午
 */
@Slf4j
public class AESUtil {

    /**
     * 初始化IV变量
     */
    private static final String ivParameter = "1008080678586098";

    /**
     * AES加密
     *
     * @param content 需要加密的字符串
     * @param key     加密密钥, 此处使用AES-128-CBC加密模式, key需要为16位。
     * @return java.lang.String
     * @author Calisto
     * @date 2021/4/24
     **/
    public static String encrypt(String content, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = key.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        // 此处使用BASE64做转码
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * AES解密
     *
     * @param content 解密的字符串
     * @param key     加密密钥, 此处使用AES-128-CBC加密模式, key需要为16位
     * @return java.lang.String
     * @author Calisto
     * @date 2021/4/24
     **/
    public static String decrypt(String content, String key) throws Exception {
        byte[] raw = key.getBytes(StandardCharsets.US_ASCII);
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        //先用base64解密
        byte[] encrypted1 = Base64.decodeBase64(content);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, StandardCharsets.UTF_8);
    }

    /**
     * 生成一个16位的密钥
     *
     * @return java.lang.String
     * @author Calisto
     * @date 2021/4/24
     **/
    public static String generateKey() {
        int digit = 16;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            result.append(Integer.toHexString(new Random().nextInt(16)));
        }
        return result.toString().toUpperCase();
    }
}
