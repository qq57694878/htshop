package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.constant.CommonConstants;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.common.exception.BussinessException;
import com.kulongtai.mpstore.common.mp.sdk.IpKit;
import com.kulongtai.mpstore.common.mp.sdk.PaymentException;
import com.kulongtai.mpstore.common.mp.sdk.WxaOrder;
import com.kulongtai.mpstore.common.mp.sdk.WxaPayApi;
import com.kulongtai.mpstore.dto.OrderAndPayDto;
import com.kulongtai.mpstore.dto.OrderIdDto;
import com.kulongtai.mpstore.entity.Order;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.IConfigService;
import com.kulongtai.mpstore.service.IOrderService;
import com.kulongtai.mpstore.service.ISkuService;
import com.kulongtai.mpstore.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/mpapi/order")
public class OrderController {
    @Autowired
    private IConfigService iConfigService;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ISkuService iSkuService;


    @Value("${mpstore.service-url}")
    private String serviceUrl;

    @GetMapping("/pay")
    @ApiOperation(value="支付订单")
    public R< Map<String, String>> pay(@RequestBody OrderIdDto p,HttpServletRequest request) throws PaymentException {
        Integer userId = BaseContextHandler.getUserId();
        Order order = iOrderService.getById(p.getOrderId());
        if(order ==null){
            throw new BussinessException("订单不存在！");
        }
        if("1".equals(order.getPayStatus())){
            throw new BussinessException("订单已支付！");
        }
        //调用微信支付统一下单
        String appid =iConfigService.getAppid();//小程序ID	appid	是
        String mchid = iConfigService.getMchid();// 商户号	mch_id	是
        String signKey = iConfigService.getPaykey();
        //设备号	device_info	否
        //随机字符串	nonce_str	是
        //签名	sign	是
        //签名类型	sign_type	否

        WxaOrder wxaOrder = new WxaOrder(appid,mchid,signKey);
        wxaOrder.setBody("韩泰轮胎-"+p.getOrderId());//商品描述	body	是
       //商品详情	detail	否
        //附加数据	attach	否
        wxaOrder.setOutTradeNo(""+p.getOrderId());//商户订单号	out_trade_no	是
        //标价币种	fee_type	否
        BigDecimal total_fee = order.getPayPrice().multiply(new BigDecimal(100));
        wxaOrder.setTotalFee( String.valueOf(total_fee.intValue())); //标价金额	total_fee	是
        String ip = IpKit.getRealIp(request);
        if (StringUtils.isEmpty(ip)) {
            ip = "127.0.0.1";
        }
        wxaOrder.setSpbillCreateIp(ip);//终端IP	spbill_create_ip	是
        //交易起始时间	time_start	否
        //交易结束时间	time_expire	否
        // 订单优惠标记	goods_tag	否
        wxaOrder.setNotifyUrl(serviceUrl+"/mpapi/public/notify/wxPayNotify");//通知地址	notify_url	是
        User user = iUserService.getById(userId);
        wxaOrder.setOpenId(user.getOpenid());//用户标识	openid	否


        //交易类型	trade_type	是
        //商品ID	product_id	否
        //指定支付方式	limit_pay	否
        //电子发票入口开放标识	receipt	否
        //场景信息	scene_info	否

        Map<String, String> packageParams = WxaPayApi.unifiedOrder(wxaOrder);
        return new R(packageParams);
    }

    @PostMapping("/orderAndPay")
    @ApiOperation(value="下单并支付")
    public R< Map<String, String>> orderAndPay(@RequestBody OrderAndPayDto p, HttpServletRequest request) throws PaymentException {
        Integer userId = BaseContextHandler.getUserId();
        if(CommonConstants.VISITOR_USER_ID.equals(userId)){
            throw new BussinessException("亲，先注册再下单才不不至于白白花钱哦！");
        }
        Sku sku = iSkuService.getById(p.getSkuId());
        Order order = new Order();
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setPayPrice(sku.getSkuPrice());
        order.setPayStatus("0");//支付状态(0:未支付;1:已支付)
        order.setSkuId(sku.getSkuId());
        iOrderService.save(order);
        Integer orderId =order.getOrderId();

        //调用微信支付统一下单
        String appid =iConfigService.getAppid();//小程序ID	appid	是
        String mchid = iConfigService.getMchid();// 商户号	mch_id	是
        String signKey = iConfigService.getPaykey();
        //设备号	device_info	否
        //随机字符串	nonce_str	是
        //签名	sign	是
        //签名类型	sign_type	否

        WxaOrder wxaOrder = new WxaOrder(appid,mchid,signKey);
        wxaOrder.setBody("韩泰轮胎-"+orderId);//商品描述	body	是
        //商品详情	detail	否
        //附加数据	attach	否
        wxaOrder.setOutTradeNo(""+orderId);//商户订单号	out_trade_no	是
        //标价币种	fee_type	否
        BigDecimal total_fee = order.getPayPrice().multiply(new BigDecimal(100));
        wxaOrder.setTotalFee( String.valueOf(total_fee.intValue())); //标价金额	total_fee	是
        String ip = IpKit.getRealIp(request);
        if (StringUtils.isEmpty(ip)) {
            ip = "127.0.0.1";
        }
        wxaOrder.setSpbillCreateIp(ip);//终端IP	spbill_create_ip	是
        //交易起始时间	time_start	否
        //交易结束时间	time_expire	否
        // 订单优惠标记	goods_tag	否
        wxaOrder.setNotifyUrl(serviceUrl+"/mpapi/public/notify/wxPayNotify");//通知地址	notify_url	是
        User user = iUserService.getById(userId);
        wxaOrder.setOpenId(user.getOpenid());//用户标识	openid	否


        //交易类型	trade_type	是
        //商品ID	product_id	否
        //指定支付方式	limit_pay	否
        //电子发票入口开放标识	receipt	否
        //场景信息	scene_info	否

        Map<String, String> packageParams = WxaPayApi.unifiedOrder(wxaOrder);
        return new R(packageParams);
    }
}
