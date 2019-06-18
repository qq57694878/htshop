package com.kulongtai.mpstore.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.mp.sdk.ApiResult;
import com.kulongtai.mpstore.common.mp.sdk.WxaUserApi;
import com.kulongtai.mpstore.common.util.JwtTokenUtil;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2019/6/13 0013.
 */
@RestController
@RequestMapping("/mpapi")
public class LoginController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IUserService iUserService;
    @GetMapping("/login")
    public R<String> login(@RequestParam String code){
        ApiResult apiResult =WxaUserApi.code2Session(code);
        String openid = apiResult.get("openid");
        String sessionKey =  apiResult.get("session_key");
        //1.用户有则修改，无则添加
        User user = iUserService.getOne(Wrappers.<User>query().eq("openid",openid).last(" limit 1"));
        if(user==null){
            user= new User();
            user.setOpenid(openid);
        }
        user.setSessionKey(sessionKey);
        iUserService.saveOrUpdate(user);
        //2.返回token
        String token = jwtTokenUtil.generateToken(String.valueOf(user.getUserId()),null);
        return new R(token);
    }

}
