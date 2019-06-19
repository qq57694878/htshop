package com.kulongtai.mpstore.service.impl;

import com.kulongtai.mpstore.entity.Order;
import com.kulongtai.mpstore.mapper.OrderMapper;
import com.kulongtai.mpstore.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
