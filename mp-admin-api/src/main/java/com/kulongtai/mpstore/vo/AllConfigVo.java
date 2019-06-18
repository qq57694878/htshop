package com.kulongtai.mpstore.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lijinliang
 * @date 2019/6/11 13:49
 */
@Data
public class AllConfigVo implements Serializable{
    @ApiModelProperty(value = "小程序id")
    private String appid;
    @ApiModelProperty(value = "小程序秘钥")
    private String appsecret;
    @ApiModelProperty(value = "微信支付商户号")
    private String mchid;
    @ApiModelProperty(value = "微信支付key")
    private String paykey;
    @ApiModelProperty(value = "服务电话")
    private String serverPhoneNumber;
}
