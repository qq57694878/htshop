package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.mp.sdk.WxaConfig;
import com.kulongtai.mpstore.common.mp.sdk.WxaConfigKit;
import com.kulongtai.mpstore.common.mp.sdk.WxaMessageApi;
import com.kulongtai.mpstore.common.util.bcrypt.BCryptPasswordEncoder;
import com.kulongtai.mpstore.dto.ResetPasswordDto;
import com.kulongtai.mpstore.dto.SkuListDto;
import com.kulongtai.mpstore.dto.UserListDto;
import com.kulongtai.mpstore.entity.About;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.IConfigService;
import com.kulongtai.mpstore.service.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 微信用户表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-02
 */
@RestController
@RequestMapping("/api/wxuser")
@Slf4j
public class UserController {
    @Value("${mpstore.defaultPassword}")
    private String defaultPassword;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IConfigService iConfigService;

    @GetMapping("/getUserList")
    @ApiOperation(value = "查询用户列表", notes = "需传入分页参数")
    public R<IPage> getUserList(UserListDto userListDto) {
        QueryWrapper<User> queryWrapper = Wrappers.<User>query();
        queryWrapper.like(StringUtils.isNotBlank(userListDto.getCarNo()), "car_no", userListDto.getCarNo())
                .eq(StringUtils.isNotBlank(userListDto.getMobile()), "mobile", userListDto.getMobile())
                .gt(userListDto.getStartTime() != null, "create_time", userListDto.getStartTime())
                .lt(userListDto.getEndTime() != null, "create_time", userListDto.getEndTime())
                .eq("del_flag", "0")
                .orderByDesc("create_time");
        IPage<User> skuList = iUserService.page(new Page<>(userListDto.getCurrent(), userListDto.getSize()), queryWrapper);
        return new R(skuList);
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码")
    public R<Boolean> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        User user = new User();
        user.setUserId(resetPasswordDto.getUserId());
        user.setPassword(passwordEncoder.encode(defaultPassword));
        iUserService.updateById(user);
        //3.发送服务消息，下发密码重置通知
        User u = iUserService.getById(resetPasswordDto.getUserId());
        if (StringUtils.isNotEmpty(u.getOpenid())) {
            try {
                WxaConfig wxaConfig = new WxaConfig();
                wxaConfig.setAppId(iConfigService.getAppid());
                wxaConfig.setAppSecret(iConfigService.getAppsecret());
                WxaConfigKit.setWxaConfig(wxaConfig);
                StringBuilder message = new StringBuilder();
                String currentTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                message.append("您的登录密码被重置为:"+defaultPassword);
                WxaMessageApi.sendText(u.getOpenid(), message.toString());
            } catch (Exception e) {
                log.error("重置用户(" + u.getUserId() + ")密码服务消息发送失败");
            }
        }
        return new R(true);
    }

}
