package com.kulongtai.mpstore.service.impl;

import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.mapper.UserMapper;
import com.kulongtai.mpstore.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
