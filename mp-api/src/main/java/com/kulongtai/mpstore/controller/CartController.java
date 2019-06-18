package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.dto.CartAddDto;
import com.kulongtai.mpstore.dto.CartSubDto;
import com.kulongtai.mpstore.entity.About;
import com.kulongtai.mpstore.entity.Cart;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.service.IAboutService;
import com.kulongtai.mpstore.service.ICartService;
import com.kulongtai.mpstore.service.ISkuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-17
 */
@RestController
@RequestMapping("/mpapi/cart")
public class CartController {
    @Autowired
    private ISkuService iSkuService;
    @Autowired
    private ICartService iCartService;
    @PostMapping("/list")
    @ApiOperation(value = "查询购物车列表")
    public R<List> list(@RequestBody CartAddDto cartAddDto) {
        Integer userId = BaseContextHandler.getUserID();
        QueryWrapper<Cart> queryWrapper = Wrappers.<Cart>query();
        queryWrapper.eq("user_id", userId);
        List<Cart> carts = iCartService.list(queryWrapper);
        List<Integer> ids = new ArrayList<Integer>();
        if (carts != null) {
            carts.forEach(item->{
                ids.add(item.getSkuId());
            });
        }
        List<Sku> skuList = (List<Sku>) iSkuService.listByIds(ids);
        return new R(skuList);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加购物车")
    public R<Boolean> add(@RequestBody CartAddDto cartAddDto) {
        Integer userId = BaseContextHandler.getUserID();
        QueryWrapper<Cart> queryWrapper = Wrappers.<Cart>query();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("sku_id", cartAddDto.getSkuId());
        queryWrapper.last(" limit 1");
        Cart cart = iCartService.getOne(queryWrapper);

        if (cart != null) {
            cart.setSkuNum(cart.getSkuNum() + cartAddDto.getSkuNum());
            iCartService.updateById(cart);
        } else {
            cart = new Cart();
            cart.setSkuNum(cartAddDto.getSkuNum());
            cart.setUserId(userId);
            cart.setSkuId(cartAddDto.getSkuId());
            iCartService.save(cart);
        }
        return new R(true);
    }

    @PostMapping("/sub")
    @ApiOperation(value = "减购物车")
    public R<Boolean> sub(@RequestBody CartSubDto cartSubDto) {
        Integer userId = BaseContextHandler.getUserID();
        QueryWrapper<Cart> queryWrapper = Wrappers.<Cart>query();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("sku_id", cartSubDto.getSkuId());
        queryWrapper.last(" limit 1");
        Cart cart = iCartService.getOne(queryWrapper);

        if (cart.getSkuNum() - 1 > 0) {
            cart.setSkuNum(cart.getSkuNum() - 1);
            iCartService.updateById(cart);
        } else {
            iCartService.removeById(cart.getCartId());
        }
        return new R(true);
    }
    @PostMapping("/delete")
    @ApiOperation(value = "删除购物车")
    public R<Boolean> delete(@RequestBody CartSubDto cartSubDto) {
        Integer userId = BaseContextHandler.getUserID();
        UpdateWrapper<Cart> updateWrapper = Wrappers.<Cart>update();
        updateWrapper.eq("user_id",userId);
        updateWrapper.eq("sku_id", cartSubDto.getSkuId());
        iCartService.remove(updateWrapper);
        return new R(true);
    }
}
