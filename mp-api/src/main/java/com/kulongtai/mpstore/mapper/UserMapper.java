package com.kulongtai.mpstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kulongtai.mpstore.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 微信用户表 Mapper 接口
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
public interface UserMapper extends BaseMapper<User> {

    void clearOpenid(@Param("openid") String openid);
}
