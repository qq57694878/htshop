package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.common.mp.sdk.WxaUserApi;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 微信用户表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/mpapi/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @PostMapping("/updateUserInfo")
    public R<Boolean> updateUserInfo(@RequestBody User user){
        user.setUserId(BaseContextHandler.getUserID());
        iUserService.updateById(user);
        return new R(true);
    }
    @GetMapping("/getUserInfo")
    public R<User> getUserInfo(){
        Integer userId = BaseContextHandler.getUserID();
        User user= iUserService.getById(userId);
        return new R(user);
    }
}
