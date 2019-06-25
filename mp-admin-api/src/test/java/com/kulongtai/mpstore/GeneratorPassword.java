package com.kulongtai.mpstore;

import com.kulongtai.mpstore.common.util.bcrypt.BCryptPasswordEncoder;

/**
 * @author Lijinliang
 * @date 2019/6/25 10:59
 */
public class GeneratorPassword {

    public static void main(String[] args) {
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       String s =  passwordEncoder.encode("123456");
        System.out.println(s);
    }
}
