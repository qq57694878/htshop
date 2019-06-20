package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.service.IAboutService;
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
   public R<List> getMyCardList(@RequestParam(value = "validFlag",required = false) String validFalg){
        Integer userId = BaseContextHandler.getUserId();
        QueryWrapper<Card>queryWrapper =  Wrappers.<Card>query();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq(StringUtils.isNotEmpty(validFalg),"valid_flag",validFalg);
        List<Card> cardList = iCardService.list(queryWrapper);
        return new R(cardList);
   }


}
