package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.dto.SkuListDto;
import com.kulongtai.mpstore.dto.UserListDto;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class UserController {
    @Autowired
    private IUserService iUserService;
    @GetMapping("/getUserList")
    @ApiOperation(value="查询用户列表", notes="需传入分页参数")
    public R<IPage> getUserList(UserListDto userListDto) {
        QueryWrapper<User> queryWrapper = Wrappers.<User>query();
        queryWrapper.like(StringUtils.isNotBlank(userListDto.getUserId()),"user_id",userListDto.getUserId())
                .eq(StringUtils.isNotBlank(userListDto.getMobile()),"mobile",userListDto.getMobile())
                .gt(userListDto.getStartTime()!=null,"create_time",userListDto.getStartTime())
                .lt(userListDto.getEndTime()!=null,"create_time",userListDto.getEndTime())
                .eq("del_flag","0")
                .orderByDesc("create_time");
        IPage<User> skuList = iUserService.page(new Page<>(userListDto.getCurrent(),userListDto.getSize()),queryWrapper);
        return new R(skuList);
    }
}
