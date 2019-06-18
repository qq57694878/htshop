package com.kulongtai.mpstore.controller;

import com.kulongtai.mpstore.common.annotation.IgnoreUserToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/5/1 0001.
 */
@RestController
@RequestMapping("/")
public class HiController {
    @GetMapping("/public/hello")
    public String hello(){
        return "hello";
    }
    @IgnoreUserToken
    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }
    @GetMapping("/ha")
    public String ha(){
        return "ha";
    }
}
