package com.kulongtai.mpstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.exception.BusinessException;
import com.kulongtai.mpstore.common.mp.sdk.*;
import com.kulongtai.mpstore.dto.ConsumeECardDto;
import com.kulongtai.mpstore.dto.ConsumeFrequencyCardDto;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.entity.CardRecord;
import com.kulongtai.mpstore.entity.Config;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.mapper.CardMapper;
import com.kulongtai.mpstore.mapper.CardRecordMapper;
import com.kulongtai.mpstore.mapper.UserMapper;
import com.kulongtai.mpstore.service.ICardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kulongtai.mpstore.service.IConfigService;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户卡券表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-09
 */
@Slf4j
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {
    @Autowired
    private IConfigService iConfigService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CardRecordMapper cardRecordMapper;
    @Transactional
    @Override
    public boolean consumeFrequencyCard(ConsumeFrequencyCardDto consumeFrequencyCardDto){
        Integer cardNo = consumeFrequencyCardDto.getCardNo();
        Integer usedFrequency = consumeFrequencyCardDto.getUsedFrequency();

       Card card =  this.getOne(Wrappers.<Card>query().eq("card_no",cardNo));
       if(card==null){
           throw new BusinessException("失败，不存在的卡号");
       }
       if(!"1".equals(card.getValidFlag())){
            throw new BusinessException("失败，该卡已无效");
       }
        if(card.getRestFrequency()<usedFrequency){
            throw new BusinessException("失败，该卡剩余次数不足（剩余次数为:"+card.getRestFrequency()+",本次消费次数为:"+usedFrequency+"）");
        }
       //1.改变卡次数
        Card updateCard  = new Card();
        updateCard.setCardId(card.getCardId()).setUpdateTime(new Date());
        updateCard.setRestFrequency(card.getRestFrequency()-usedFrequency);
        if(card.getRestFrequency()-usedFrequency<=0){
            updateCard.setValidFlag("0"); //至为无效
        }
        updateCard.updateById();
        //2.记录消费记录
        CardRecord cardRecord = new CardRecord();
        cardRecord.setCardId(card.getCardId());
        cardRecord.setBussType(card.getBussType());
        cardRecord.setCardNo(cardNo);
        cardRecord.setBeforeUsedFrequency(card.getRestFrequency());
        cardRecord.setUsedFrequency(usedFrequency);
        cardRecord.setAfterUsedFrequency(updateCard.getRestFrequency());
        cardRecord.setUserId(card.getUserId());
        cardRecord.setCreateTime(new Date());
        cardRecordMapper.insert(cardRecord);
        //3.发送服务消息
        try{
            User user = userMapper.selectById(card.getUserId());
            Config appidConfig =  iConfigService.getOne(Wrappers.<Config>query().eq("k","appid"));
            Config secretConfig =  iConfigService.getOne(Wrappers.<Config>query().eq("k","appsecret"));
            WxaConfig wxaConfig = new WxaConfig();
            wxaConfig.setAppId(appidConfig.getV());
            wxaConfig.setAppSecret(secretConfig.getV());
            WxaConfigKit.setWxaConfig(wxaConfig);
            /*WxaAccessToken wxaAccessToken =  WxaAccessTokenApi.getAccessToken();
            String accessToken = wxaAccessToken.getAccessToken();*/
            StringBuilder message = new StringBuilder();
            String currentTime =DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
            message.append("消费提醒：您的{卡号："+card.getCardNo()+"，卡名:"+card.getCardName()+"}的服务卡本次消费"+usedFrequency+"次，使用时间:"+currentTime+",如有疑问请致电商家！");
            WxaMessageApi.sendText(user.getOpenid(),message.toString());
        }catch (Exception e){log.error("卡号："+cardNo+"，客服消息发送失败");};

        return true;
    }

    @Override
    public boolean consumeECard(ConsumeECardDto consumeECardDto){
        Integer cardNo =consumeECardDto.getCardNo();
        BigDecimal consumeMoney = consumeECardDto.getUsedPrice();
        Card card =  this.getOne(Wrappers.<Card>query().eq("card_no",consumeECardDto.getCardNo()));
        if(card==null){
            throw new BusinessException("失败，不存在的卡号");
        }
        if(!"1".equals(card.getValidFlag())){
            throw new BusinessException("失败，该卡已无效");
        }
        if(card.getBalancePrice().doubleValue()<consumeMoney.doubleValue()){
            throw new BusinessException("失败，该卡余额不足（余额为:"+card.getBalancePrice()+",本次消费金额为:"+consumeMoney+"）");
        }
        //1.改变卡余额
        Card updateCard  = new Card();
        updateCard.setCardId(card.getCardId()).setUpdateTime(new Date());
        updateCard.setBalancePrice(card.getBalancePrice().subtract(consumeMoney));
        if(card.getBalancePrice().subtract(consumeMoney).doubleValue()<=0){
            updateCard.setValidFlag("0"); //至为无效
        }
        updateCard.updateById();
        //1.改变卡状态,当余额为零时
        if(card.getBalancePrice().subtract(consumeMoney).doubleValue()<=0){
            new Card().setCardId(card.getCardId()).setValidFlag("0").setUpdateTime(new Date()).updateById();
        }
        //2.记录消费记录
        CardRecord cardRecord = new CardRecord();
        cardRecord.setCardId(card.getCardId());
        cardRecord.setBussType(card.getBussType());
        cardRecord.setCardNo(cardNo);
        cardRecord.setBeforeUsedPrice(card.getBalancePrice());
        cardRecord.setUsedPrice(consumeMoney);
        cardRecord.setAfterUsedPrice(card.getBalancePrice().subtract(consumeMoney));
        cardRecord.setUserId(card.getUserId());
        cardRecord.setCreateTime(new Date());
        cardRecordMapper.insert(cardRecord);
        //3.发送服务消息
        try{
            User user = userMapper.selectById(card.getUserId());
            Config appidConfig =  iConfigService.getOne(Wrappers.<Config>query().eq("k","appid"));
            Config secretConfig =  iConfigService.getOne(Wrappers.<Config>query().eq("k","appsecret"));
            WxaConfig wxaConfig = new WxaConfig();
            wxaConfig.setAppId(appidConfig.getV());
            wxaConfig.setAppSecret(secretConfig.getV());
            WxaConfigKit.setWxaConfig(wxaConfig);
            /*WxaAccessToken wxaAccessToken =  WxaAccessTokenApi.getAccessToken();
            String accessToken = wxaAccessToken.getAccessToken();*/
            String currentTime =DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
            StringBuilder message = new StringBuilder();
            message.append("消费提醒：您的{卡号："+card.getCardNo()+"，卡名:"+card.getCardName()+"}的E卡本次消费"+consumeMoney+"，余额为"+cardRecord.getAfterUsedPrice()+",消费时间："+currentTime+",如有疑问请致电商家！");
            WxaMessageApi.sendText(user.getOpenid(),message.toString());
        }catch (Exception e){log.error("卡号："+cardNo+"，客服消息发送失败");};

        return true;
    }
}
