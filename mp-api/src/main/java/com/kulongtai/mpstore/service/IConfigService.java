package com.kulongtai.mpstore.service;

import com.kulongtai.mpstore.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
public interface IConfigService extends IService<Config> {
    public String getAppid();
    public String getAppsecret();
    public String getMchid();
    public String getPaykey();
    public String getServerPhoneNumber();
}
