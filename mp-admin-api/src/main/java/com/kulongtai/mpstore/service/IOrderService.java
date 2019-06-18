package com.kulongtai.mpstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kulongtai.mpstore.dto.OrderListDto;
import com.kulongtai.mpstore.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kulongtai.mpstore.vo.OrderInfo;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-02
 */
public interface IOrderService extends IService<Order> {

    IPage<OrderInfo> getOrderListByPage(OrderListDto orderListDto);
}
