package com.kulongtai.mpstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kulongtai.mpstore.dto.ConsumeCardDto;
import com.kulongtai.mpstore.entity.Card;

/**
 * <p>
 * 用户卡券表 服务类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-09
 */
public interface ICardService extends IService<Card> {

    boolean consumeCard(ConsumeCardDto consumeCardDto);
}
