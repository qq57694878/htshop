package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.entity.CardRecord;
import com.kulongtai.mpstore.service.ICardRecordService;
import com.kulongtai.mpstore.service.ICardService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户卡券消费记录表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/mpapi/card-record")
public class CardRecordController {

    @Autowired
    private ICardRecordService iCardRecordService;

    @GetMapping("/getCardRecordList")
    @ApiOperation(value="查询本卡的消费记录")
    public R<List> getCardRecordList(@RequestParam(value = "cardId") Integer cardId){
        QueryWrapper<CardRecord> queryWrapper =  Wrappers.<CardRecord>query();
        queryWrapper.eq("card_id",cardId);
        List<CardRecord> cardList = iCardRecordService.list(queryWrapper);
        return new R(cardList);
    }
}
