package com.kulongtai.mpstore.init;

import com.kulongtai.mpstore.common.mp.sdk.WxaConfig;
import com.kulongtai.mpstore.common.mp.sdk.WxaConfigKit;
import com.kulongtai.mpstore.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2019/6/13 0013.
 */
@Component
public class ConfigInit {
    @Autowired
    private IConfigService iConfigService;
    @PostConstruct
    public void init(){

        WxaConfig wxaConfig = new WxaConfig();
        wxaConfig.setAppId( iConfigService.getAppid());
        wxaConfig.setAppSecret(iConfigService.getAppsecret());
        WxaConfigKit.setWxaConfig(wxaConfig);
    }
}
