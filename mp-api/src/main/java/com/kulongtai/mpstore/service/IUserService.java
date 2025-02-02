package com.kulongtai.mpstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kulongtai.mpstore.entity.User;

/**
 * <p>
 * 微信用户表 服务类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
public interface IUserService extends IService<User> {

    void clearOpenid(String openid);
}
