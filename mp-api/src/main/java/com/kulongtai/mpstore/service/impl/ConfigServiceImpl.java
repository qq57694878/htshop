package com.kulongtai.mpstore.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.entity.Config;
import com.kulongtai.mpstore.mapper.ConfigMapper;
import com.kulongtai.mpstore.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

    @Override
    public String getAppid() {
        return this.getOne(Wrappers.<Config>query().eq("k","appid").last(" limit 1 ")).getV();
    }

    @Override
    public String getAppsecret() {
        return this.getOne(Wrappers.<Config>query().eq("k","appsecret").last(" limit 1 ")).getV();
    }

    @Override
    public String getMchid() {
        return this.getOne(Wrappers.<Config>query().eq("k","mchid").last(" limit 1 ")).getV();
    }

    @Override
    public String getPaykey() {
        return this.getOne(Wrappers.<Config>query().eq("k","paykey").last(" limit 1 ")).getV();
    }

    @Override
    public String getServerPhoneNumber() {
        return this.getOne(Wrappers.<Config>query().eq("k","server_phone_number").last(" limit 1 ")).getV();
    }
}
