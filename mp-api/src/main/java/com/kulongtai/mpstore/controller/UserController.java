package com.kulongtai.mpstore.controller;


import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
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
        user.setUserId(BaseContextHandler.getUserId());
        iUserService.updateById(user);
        return new R(true);
    }
    @GetMapping("/getUserInfo")
    public R<User> getUserInfo(){
        Integer userId = BaseContextHandler.getUserId();
        User user= iUserService.getById(userId);
        return new R(user);
    }
}
