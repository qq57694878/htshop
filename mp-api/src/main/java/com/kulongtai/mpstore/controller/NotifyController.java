package com.kulongtai.mpstore.controller;

import com.kulongtai.mpstore.common.mp.util.HttpKit;
import com.kulongtai.mpstore.common.mp.util.PaymentKit;
import com.kulongtai.mpstore.entity.Order;
import com.kulongtai.mpstore.service.IConfigService;
import com.kulongtai.mpstore.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/16 0016.
 */
@Controller
@RequestMapping("/mpapi/public/notify")
public class NotifyController {
    @Autowired
    private IConfigService iConfigService;
    @Autowired
    private IOrderService iOrderService;
    @ResponseBody
    @ApiOperation(value="微信结果通知")
    @RequestMapping("/wxPayNotify")
    public String wxPayNotify(HttpServletRequest request){
       // 支付结果通用通知文档: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
        String xmlMsg = HttpKit.readData(request);
        System.out.println("支付通知="+xmlMsg);
        Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);

        String result_code  = params.get("result_code");

        // 商户订单号
        String orderIdStr      = params.get("out_trade_no");


        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 避免已经成功、关闭、退款的订单被再次更新
         String result ="";
        if(PaymentKit.verifyNotify(params, iConfigService.getPaykey())){
            if (("SUCCESS").equals(result_code)) {
                //更新订单信息
                System.out.println("更新订单信息");
                Integer orderId = Integer.parseInt(orderIdStr);
                Order order =  iOrderService.getById(orderId);
                if("0".equals(order.getPayStatus())){
                    iOrderService.processPayedOrder(params);
                }
                Map<String, String> xml = new HashMap<String, String>();
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                result =  PaymentKit.toXml(xml);
            }
        }
        return result;
    }
}
