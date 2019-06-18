package com.kulongtai.mpstore.service.impl;

import com.kulongtai.mpstore.entity.Cart;
import com.kulongtai.mpstore.mapper.CartMapper;
import com.kulongtai.mpstore.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-17
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
