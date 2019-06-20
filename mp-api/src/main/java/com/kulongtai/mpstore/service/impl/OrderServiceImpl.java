package com.kulongtai.mpstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.util.NoGenerator;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.entity.Order;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.mapper.OrderMapper;
import com.kulongtai.mpstore.service.ICardService;
import com.kulongtai.mpstore.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kulongtai.mpstore.service.ISkuService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private ICardService iCardService;
    @Autowired
    private ISkuService iSkuService;

    @Transactional
    @Override
    public void processPayedOrder(Map<String,String> payResult) {
        // 总金额
        String totalFee     = payResult.get("total_fee");
        // 微信支付订单号
        String transId      = payResult.get("transaction_id");
        // 支付完成时间，格式为yyyyMMddHHmmss
        String timeEnd      = payResult.get("time_end");
        // 商户订单号
        String orderIdStr      = payResult.get("out_trade_no");

        Integer orderId = Integer.parseInt(orderIdStr);
        Order order = this.getById(orderId);
        //1.更新订单状态，支付时间，支付状态，更新时间
        order.setPayStatus("1");
        order.setUpdateTime(new Date());
        order.setPayTime(DateUtil.parse(timeEnd,"yyyyMMddHHmmss"));
        this.updateById(order);
        Sku sku = iSkuService.getById(orderId);

        //2.发放卡片

        Card card = new Card();
        card.setUserId(order.getUserId());
        card.setOrderId(order.getOrderId());
        card.setValidFlag("1");
        card.setCardNo(NoGenerator.cardNo());
        card.setCardContent(sku.getSkuContent());
        card.setCardName(sku.getSkuName());
        card.setRestFrequency(sku.getFrequency());
        card.setTotalFrequency(sku.getFrequency());
        card.setUpdateTime(new Date());
        card.setCreateTime(new Date());
        card.setSkuId(String.valueOf(sku.getSkuId()));
        //有效期生成
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, sku.getValidMonth());
        card.setValideTime(c.getTime());
        iCardService.save(card);
    }
}
