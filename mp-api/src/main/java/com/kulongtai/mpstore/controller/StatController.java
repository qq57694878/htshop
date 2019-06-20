package com.kulongtai.mpstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.common.context.BaseContextHandler;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.entity.User;
import com.kulongtai.mpstore.service.ICardService;
import com.kulongtai.mpstore.service.IUserService;
import com.kulongtai.mpstore.vo.MyStatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019/6/21 0021.
 */
@RestController
@RequestMapping("/mpapi/stat")
public class StatController {
    @Autowired
    private ICardService iCardService;
    @GetMapping("/mystat")
    public R<MyStatVo> mystat(){
        Integer userId =BaseContextHandler.getUserId();
        QueryWrapper<Card> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id",userId).eq("valid_flag","1");
        Integer cardCount = iCardService.count(queryWrapper);
        MyStatVo r = new MyStatVo();
        r.setCardCount(cardCount);
        return new R(r);
    }
}
