package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.entity.Config;
import com.kulongtai.mpstore.service.IConfigService;
import com.kulongtai.mpstore.vo.AllConfigVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配置表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-09
 */
@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    private IConfigService iConfigService;

    @GetMapping("/getAllConfig")
    @ApiOperation(value="查询所有的配置")
    public R<AllConfigVo> getAllConfig(){
        AllConfigVo result = new AllConfigVo();
        List<Config> list = iConfigService.list();
        Map<String,String> m = new HashMap<String,String>();
        if(list!=null&&list.size()>0){
            list.forEach(item->{
                m.put(item.getK(),item.getV());
            });
        }
        result.setAppid(m.get("appid"));
        result.setAppsecret(m.get("appsecret"));
        result.setMchid(m.get("mchid"));
        result.setPaykey(m.get("paykey"));
        result.setServerPhoneNumber(m.get("server_phone_number"));
        return new R(result);
    }
    @PostMapping("/saveAllConfig")
    @ApiOperation(value="保存所有的配置")
    public R<Boolean> saveAllConfig(@RequestBody  AllConfigVo allConfigVo){
        iConfigService.remove(Wrappers.update());
        List<Config> list = new ArrayList<>();
        list.add(new Config().setK("appid").setV(allConfigVo.getAppid()));
        list.add(new Config().setK("appsecret").setV(allConfigVo.getAppsecret()));
        list.add(new Config().setK("mchid").setV(allConfigVo.getMchid()));
        list.add(new Config().setK("paykey").setV(allConfigVo.getPaykey()));
        list.add(new Config().setK("server_phone_number").setV(allConfigVo.getServerPhoneNumber()));
        iConfigService.saveBatch(list);
        return new R(true);
    }
}
