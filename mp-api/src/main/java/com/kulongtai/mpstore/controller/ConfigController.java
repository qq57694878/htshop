package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.entity.Config;
import com.kulongtai.mpstore.service.IConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配置表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/mpapi/config")
public class ConfigController {
    @Autowired
    private IConfigService iConfigService;
    @GetMapping("/getConfig")
    @ApiOperation(value="查询配置信息")
    public R<String> getConfig(@RequestParam String k){
        String phone="";
         Config  config =  iConfigService.getOne(Wrappers.<Config>query().eq("k","server_phone_number").last(" limit 1 "));
        if(config!=null){
            phone = config.getV();
        }
        return new R(phone);
    }
}
