package com.kulongtai.mpstore;

import com.kulongtai.mpstore.common.util.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Administrator on 2019/6/24 0024.
 */
public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
       String s=  b.encode("123456");
        System.out.println(s);
    }
}
