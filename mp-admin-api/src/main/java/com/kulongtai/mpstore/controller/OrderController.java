package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.dto.OrderListDto;
import com.kulongtai.mpstore.entity.Order;
import com.kulongtai.mpstore.entity.OrderSku;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.IOrderService;
import com.kulongtai.mpstore.service.IOrderSkuService;
import com.kulongtai.mpstore.service.ISkuService;
import com.kulongtai.mpstore.service.IUserService;
import com.kulongtai.mpstore.vo.OrderInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-02
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderSkuService iOrderSkuService;

    @GetMapping("/getOrderList")
    @ApiOperation(value="查询订单列表", notes="需传入分页参数")
    public R<IPage> getOrderList(OrderListDto orderListDto) {
        IPage<OrderInfo> page =  iOrderService.getOrderListByPage(orderListDto);
        List<OrderInfo> orderList = page.getRecords();
        if(orderList!=null&&orderList.size()>0){
            List<Integer>orderIdlist = orderList.stream().map(orderInfo -> {return orderInfo.getOrderId();}).collect(Collectors.toList());
            List<OrderSku> orderSkuList =  iOrderSkuService.list(Wrappers.<OrderSku>query().in("order_id",orderIdlist));
            Map<Integer,List<OrderSku>> orderSkuMap = new HashMap<>();
            orderSkuList.stream().forEach(o -> {
                List<OrderSku>list = orderSkuMap.get(o.getOrderId());
                if(list==null){
                    list = new ArrayList<OrderSku>();
                    orderSkuMap.put(o.getOrderId(),list);
                }
                list.add(o);
            });
            orderList.forEach(orderInfo -> {
                orderInfo.setSkuList(orderSkuMap.get(orderInfo.getOrderId()));
            });
        }
        return new R(page);
    }
    @GetMapping("/getOrderDetail")
    @ApiOperation(value="查询订单详情")
    @ApiImplicitParam(paramType="query", name = "id", value = "订单id", required = true, dataType = "Integer")
    public R getOrderDetail(@RequestParam(required = true) Integer id) {
        Order order = iOrderService.getById(id);
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(order,orderInfo);
        User user = iUserService.getById(orderInfo.getUserId());
        orderInfo.setNickname(user.getNickname());
        orderInfo.setMobile(user.getMobile());
        QueryWrapper<OrderSku>queryWrapper = Wrappers.query();
        queryWrapper.eq("order_id",id);
        List<OrderSku> skuList =  iOrderSkuService.list(queryWrapper);
        orderInfo.setSkuList(skuList);
        return new R(orderInfo);
    }
}
