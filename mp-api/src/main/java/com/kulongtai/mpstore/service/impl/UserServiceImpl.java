package com.kulongtai.mpstore.service.impl;

import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.mapper.UserMapper;
import com.kulongtai.mpstore.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信用户表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void clearOpenid(String openid) {
        userMapper.clearOpenid(openid);
    }
}
