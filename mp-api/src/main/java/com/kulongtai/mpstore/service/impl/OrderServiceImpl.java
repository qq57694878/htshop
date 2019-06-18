package com.kulongtai.mpstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.util.NoGenerator;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.entity.Order;
import com.kulongtai.mpstore.entity.OrderSku;
import com.kulongtai.mpstore.mapper.OrderMapper;
import com.kulongtai.mpstore.service.ICardService;
import com.kulongtai.mpstore.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kulongtai.mpstore.service.IOrderSkuService;
import com.kulongtai.mpstore.vo.OrderInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    private IOrderSkuService iOrderSkuService;
    @Transactional
    @Override
    public void processPayedOrder(Integer orderId) {
        Order order = this.getById(orderId);
        //1.更新订单状态，支付时间，支付状态，更新时间
        order.setPayStatus("1");
        order.setUpdateTime(new Date());
        order.setPayTime(new Date());
        order.setOrderStatus("8");
        this.updateById(order);
        //2.发放卡片
        QueryWrapper<OrderSku> queryWrapper = Wrappers.query();
        queryWrapper.eq("order_id",orderId);
        List<OrderSku> skuList =  iOrderSkuService.list(queryWrapper);
        skuList.forEach(sku->{
            Card card = new Card();
            card.setUserId(order.getUserId());
            card.setOrderId(order.getOrderId());
            card.setValidFlag("1");
            card.setBalancePrice(sku.getFacePrice());
            card.setBussType(sku.getBussType());
            card.setCardNo(NoGenerator.cardNo(sku.getBussType()));
            card.setFacePrice(sku.getFacePrice());
            card.setCardContent(sku.getSkuContent());
            card.setCardDesc(sku.getSkuDesc());
            card.setCardName(sku.getSkuName());
            card.setOrderSkuId(sku.getId());
            card.setRestFrequency(sku.getFrequency());
            card.setTotalFrequency(sku.getFrequency());
            card.setUpdateTime(new Date());
            card.setCreateTime(new Date());
            card.setSkuId(String.valueOf(sku.getSkuId()));
            iCardService.save(card);
        });



    }
}
