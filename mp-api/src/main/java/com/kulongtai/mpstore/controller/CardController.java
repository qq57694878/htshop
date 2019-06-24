package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.dto.CardListDto;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.service.ICardService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户卡券表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/mpapi/card")
public class CardController {
    @Autowired
    private ICardService iCardService;
    @GetMapping("/getMyCardList")
    @ApiOperation(value="查询我的卡券列表")
   public  R<IPage> getMyCardList(CardListDto cardListDto){
        Integer userId = BaseContextHandler.getUserId();
        QueryWrapper<Card>queryWrapper =  Wrappers.<Card>query();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq(StringUtils.isNotEmpty(cardListDto.getValidFlag()),"valid_flag",cardListDto.getValidFlag());
        queryWrapper.orderByDesc("create_time");
        IPage<Card> cardList = iCardService.page(new Page<Card>(cardListDto.getCurrent(),cardListDto.getSize()),queryWrapper);
        return new R(cardList);
   }


}
