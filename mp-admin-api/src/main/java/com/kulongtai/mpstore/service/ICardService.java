package com.kulongtai.mpstore.service;

import com.kulongtai.mpstore.dto.ConsumeECardDto;
import com.kulongtai.mpstore.dto.ConsumeFrequencyCardDto;
import com.kulongtai.mpstore.entity.Card;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 用户卡券表 服务类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-09
 */
public interface ICardService extends IService<Card> {


    boolean consumeECard(ConsumeECardDto consumeECardDto);

    boolean consumeFrequencyCard(ConsumeFrequencyCardDto consumeFrequencyCardDto);
}
