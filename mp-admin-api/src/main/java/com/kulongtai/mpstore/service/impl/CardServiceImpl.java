package com.kulongtai.mpstore.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kulongtai.mpstore.common.exception.BusinessException;
import com.kulongtai.mpstore.common.mp.sdk.WxaConfig;
import com.kulongtai.mpstore.common.mp.sdk.WxaConfigKit;
import com.kulongtai.mpstore.common.mp.sdk.WxaMessageApi;
import com.kulongtai.mpstore.dto.ConsumeCardDto;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.entity.CardRecord;
import com.kulongtai.mpstore.entity.Config;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.mapper.CardMapper;
import com.kulongtai.mpstore.mapper.CardRecordMapper;
import com.kulongtai.mpstore.mapper.UserMapper;
import com.kulongtai.mpstore.service.ICardService;
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
    public boolean consumeCard(ConsumeCardDto consumeFrequencyCardDto){
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
            WxaConfig wxaConfig = new WxaConfig();
            wxaConfig.setAppId(iConfigService.getAppid());
            wxaConfig.setAppSecret(iConfigService.getAppsecret());
            WxaConfigKit.setWxaConfig(wxaConfig);
            /*WxaAccessToken wxaAccessToken =  WxaAccessTokenApi.getAccessToken();
            String accessToken = wxaAccessToken.getAccessToken();*/
            StringBuilder message = new StringBuilder();
            String currentTime = DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
            message.append("消费提醒：您的{卡号："+card.getCardNo()+"，卡名:"+card.getCardName()+"}的服务卡本次消费"+usedFrequency+"次，使用时间:"+currentTime+",如有疑问请致电商家！");
            WxaMessageApi.sendText(user.getOpenid(),message.toString());
        }catch (Exception e){log.error("卡号："+cardNo+"，客服消息发送失败");};

        return true;
    }

}
