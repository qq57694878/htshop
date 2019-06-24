package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.util.AESUtils;
import com.kulongtai.mpstore.dto.LoginDto;
import com.kulongtai.mpstore.dto.SkuListDto;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.entity.SysUser;
import com.kulongtai.mpstore.service.ISysUserService;
import com.xiaoleilu.hutool.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    @Value("mpstore.aesKey")
    private String aesKey;
    @Autowired
    private ISysUserService iSysUserService;

    @PostMapping("/api/login")
    @ApiOperation(value = "查询商品列表", notes = "需传入分页参数")
    public R<String> login(@RequestBody LoginDto loginDto) {
        String password = AESUtils.decryptAES(loginDto.getPassword(), aesKey).trim();
        QueryWrapper<SysUser> queryWrapper = Wrappers.query();
        SysUser sysUser = iSysUserService.getOne(queryWrapper);

        return new R();
    }

}
