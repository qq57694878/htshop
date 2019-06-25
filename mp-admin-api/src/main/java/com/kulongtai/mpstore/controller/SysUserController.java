package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonBooleanFormatVisitor;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.common.exception.BusinessException;
import com.kulongtai.mpstore.common.mp.sdk.WxaConfig;
import com.kulongtai.mpstore.common.mp.sdk.WxaConfigKit;
import com.kulongtai.mpstore.common.mp.sdk.WxaMessageApi;
import com.kulongtai.mpstore.common.util.AESUtils;
import com.kulongtai.mpstore.common.util.JwtTokenUtil;
import com.kulongtai.mpstore.common.util.bcrypt.BCryptPasswordEncoder;
import com.kulongtai.mpstore.dto.LoginDto;
import com.kulongtai.mpstore.dto.ResetPasswordDto;
import com.kulongtai.mpstore.dto.SkuListDto;
import com.kulongtai.mpstore.dto.SysResetPasswordDto;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.entity.SysUser;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.ISysUserService;
import com.xiaoleilu.hutool.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-24
 */
@RestController
public class SysUserController {
    @Value("${mpstore.aesKey}")
    private String aesKey;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/api/login")
    @ApiOperation(value = "登录", notes = "登录")
    public R<String> login(@RequestBody LoginDto loginDto) {
        String password = loginDto.getPassword();//AESUtils.decryptAES(loginDto.getPassword(), aesKey).trim();
        QueryWrapper<SysUser> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_code",loginDto.getUsername());
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        if(sysUser==null){
            throw new BusinessException("用户不存在！");
        }
        if(!passwordEncoder.matches(password,sysUser.getPassword()))  {
            throw new BusinessException("账号密码不正确");
        }
        //2.返回token
        String token = jwtTokenUtil.generateToken(String.valueOf(sysUser.getUserId()),new HashMap());
        return new R(token);
    }

    @PostMapping("/api/refeshToken")
    @ApiOperation(value = "刷新token", notes = "刷新token")
    public R<String> refeshToken() {
        Integer userId = BaseContextHandler.getUserId();
        //2.返回token
        String token = jwtTokenUtil.generateToken(String.valueOf(userId),new HashMap());
        return new R(token);
    }
    @PostMapping("/api/modifyPassword")
    @ApiOperation(value = "重置密码")
    public R<Boolean> resetPassword(@RequestBody SysResetPasswordDto sysResetPasswordDto) {
        Integer userId = BaseContextHandler.getUserId();
        SysUser sysUser =  iSysUserService.getById(userId);
        if(!passwordEncoder.matches(sysResetPasswordDto.getOldpassword(),sysUser.getPassword())){
            throw new BusinessException("原密码不正确");
        }
        sysUser.setPassword(passwordEncoder.encode(sysResetPasswordDto.getNewpassword()));
        iSysUserService.updateById(sysUser);
        return new R(true);
    }

}
