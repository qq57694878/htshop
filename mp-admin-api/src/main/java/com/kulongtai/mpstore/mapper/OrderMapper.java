package com.kulongtai.mpstore.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.dto.OrderListDto;
import com.kulongtai.mpstore.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kulongtai.mpstore.vo.OrderInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-02
 */
public interface OrderMapper extends BaseMapper<Order> {

    IPage<OrderInfo> getOrderListByPage(Page<Object> objectPage,@Param("p") OrderListDto orderListDto);
}
