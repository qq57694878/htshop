package com.kulongtai.mpstore.service.impl;

import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.mapper.CardMapper;
import com.kulongtai.mpstore.service.ICardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户卡券表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-19
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {

}
