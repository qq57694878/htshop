package com.kulongtai.mpstore.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.constant.CommonConstants;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.common.exception.BussinessException;
import com.kulongtai.mpstore.common.mp.sdk.ApiResult;
import com.kulongtai.mpstore.common.mp.sdk.WxaUserApi;
import com.kulongtai.mpstore.common.util.JwtTokenUtil;
import com.kulongtai.mpstore.common.util.bcrypt.BCryptPasswordEncoder;
import com.kulongtai.mpstore.dto.LoginDto;
import com.kulongtai.mpstore.dto.ModifyPasswordDto;
import com.kulongtai.mpstore.dto.RegDto;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.IUserService;
import com.xiaoleilu.hutool.map.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;

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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    /**
     *  此登录是返回token
     * @param code
     * @return
     */
    @GetMapping("/login")
    public R<String> login(@RequestParam("code") String code){
        ApiResult apiResult =WxaUserApi.code2Session(code);
        String openid = apiResult.get("openid");
        String sessionKey =  apiResult.get("session_key");
        //1.用户有则修改，无则添加
        User user = iUserService.getOne(Wrappers.<User>query().eq("openid",openid).last(" limit 1"));
        Integer userId = CommonConstants.VISITOR_USER_ID;
        if(user!=null){
            user.setSessionKey(sessionKey);
            user.setLastLoginTime(new Date());
            iUserService.updateById(user);
            userId = user.getUserId();
        }
        //2.返回token
        String token = jwtTokenUtil.generateToken(String.valueOf(userId),new HashMap());
        return new R(token);
    }
    /**
     * 真正的账号密码登录
     * @param loginDto
     * @return
     */
    @PostMapping("/loginByPassword")
    public R<String> loginByPassword(@RequestBody LoginDto loginDto){
        ApiResult apiResult =WxaUserApi.code2Session(loginDto.getCode());
        String openid = apiResult.get("openid");
        String sessionKey =  apiResult.get("session_key");
        User user =  iUserService.getOne(Wrappers.<User>query().eq("mobile",loginDto.getMobile()).last(" limit 1"));
        if(user==null){
            throw new BussinessException("用户不存在，请先注册");
        }
        if(!passwordEncoder.matches(loginDto.getPassword(),user.getPassword())){
            throw new BussinessException("登录失败，密码不正确");
        }
        //重新绑定新注册用户
        user.setLastLoginTime(new Date());
        user.setOpenid(openid);
        user.setSessionKey(sessionKey);
        iUserService.updateById(user);
        //2.返回token
        String token = jwtTokenUtil.generateToken(String.valueOf(user.getUserId()), new HashMap<>());
        return new R(token);
    }

    /**
     * 注册
     * @param regDto
     * @return
     */
    @Transactional
    @PostMapping("/reg")
    public R<String> reg(@RequestBody  RegDto regDto){
        ApiResult apiResult =WxaUserApi.code2Session(regDto.getCode());
        String openid = apiResult.get("openid");
        String sessionKey =  apiResult.get("session_key");
       User u =  iUserService.getOne(Wrappers.<User>query().eq("mobile",regDto.getMobile()).last(" limit 1"));
       if(u!=null){
           throw new BussinessException("该电话号码已注册，请直接登录！");
       }
        //解除原来openid的绑定
        iUserService.clearOpenid(openid);
       //重新绑定新注册用户
        User user = new User();
        user.setMobile(regDto.getMobile());
        user.setCarNo(regDto.getCarNo());
        user.setPassword(passwordEncoder.encode(regDto.getPassword()));
        user.setCreateTime(new Date());
        user.setOpenid(openid);
        user.setSessionKey(sessionKey);
        user.setLastLoginTime(new Date());
        iUserService.save(user);
        //2.返回token
        String token = jwtTokenUtil.generateToken(String.valueOf(user.getUserId()),new HashMap<>());
        return new R(token);
    }
    /**
     * 修改密码
     * @param modifyPasswordDto
     * @return
     */
    @Transactional
    @PostMapping("/modifyPasswrod")
    public R<Boolean> modifyPasswrod(@RequestBody ModifyPasswordDto modifyPasswordDto){
        Integer userId = BaseContextHandler.getUserId();
        User u =  iUserService.getOne(Wrappers.<User>query().eq("user_id",userId).last(" limit 1 "));
        if(!passwordEncoder.matches(modifyPasswordDto.getPassword(),u.getPassword())){
            throw new BussinessException("修改失败，原密码不正确");
        }

        //重新绑定新注册用户
        User user = new User();
        user.setUserId(u.getUserId());
        user.setOpenid(u.getOpenid());
        user.setPassword(passwordEncoder.encode(modifyPasswordDto.getNewPassword()));
        iUserService.updateById(user);
        return new R(true);
    }


    /**
     * 登出
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(){
        Integer userId = BaseContextHandler.getUserId();
        //重新绑定新注册用户
        User user =new User();
        user.setUserId(userId);
        user.setOpenid(null);
        iUserService.updateById(user);
        //2.返回 游客身份的token
        String token = jwtTokenUtil.generateToken( String.valueOf(CommonConstants.VISITOR_USER_ID),new HashMap<>());
        return new R(token);
    }
}
