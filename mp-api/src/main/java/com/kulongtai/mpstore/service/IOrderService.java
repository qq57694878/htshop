package com.kulongtai.mpstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kulongtai.mpstore.entity.Order;

import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
public interface IOrderService extends IService<Order> {

    void processPayedOrder(Map<String, String> payResult);
}
