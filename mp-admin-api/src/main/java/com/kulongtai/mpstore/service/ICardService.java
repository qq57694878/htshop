package com.kulongtai.mpstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kulongtai.mpstore.dto.CardListDto;
import com.kulongtai.mpstore.dto.ConsumeCardDto;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.vo.CardListVo;

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

    IPage<CardListVo> pageCardList(Page<Object> objectPage, CardListDto cardListDto);
}
