package com.kulongtai.mpstore.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.dto.OrderListDto;
import com.kulongtai.mpstore.entity.Order;
import com.kulongtai.mpstore.mapper.OrderMapper;
import com.kulongtai.mpstore.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kulongtai.mpstore.vo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-02
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public IPage<OrderInfo> getOrderListByPage(OrderListDto orderListDto) {
        return orderMapper.getOrderListByPage(new Page<>(orderListDto.getCurrent(),orderListDto.getSize()),orderListDto);

    }
}
