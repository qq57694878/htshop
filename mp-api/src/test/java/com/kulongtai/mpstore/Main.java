package com.kulongtai.mpstore;

import com.kulongtai.mpstore.common.util.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Administrator on 2019/6/24 0024.
 */
public class Main {
    private final static String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static Random RANDOM = new SecureRandom();
    public static void main(String[] args) {
     /*   BCryptPasswordEncoder b = new BCryptPasswordEncoder();
       String s=  b.encode("123456");
        System.out.println(s);*/
      String key =   generateNonceStr();
        System.out.println(key);
    }
    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
}
