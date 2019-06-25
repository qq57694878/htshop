package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.dto.CardListDto;
import com.kulongtai.mpstore.dto.CardRecordListDto;
import com.kulongtai.mpstore.entity.CardRecord;
import com.kulongtai.mpstore.service.ICardRecordService;
import com.kulongtai.mpstore.vo.CardListVo;
import com.kulongtai.mpstore.vo.CardRecordListVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户卡券消费记录表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-20
 */
@RestController
@RequestMapping("/api/card-record")
public class CardRecordController {
    @Autowired
    private ICardRecordService iCardRecordService;
    @GetMapping("/getCardRecordList")
    @ApiOperation(value="查询卡券列表", notes="需传入分页参数")
    public R<IPage> getCardRecordList(CardRecordListDto cardRecordListDto) {
        IPage<CardRecordListVo> cardRecordList = iCardRecordService.pageCardRecordList(new Page<>(cardRecordListDto.getCurrent(),cardRecordListDto.getSize()),cardRecordListDto);
        return new R(cardRecordList);
    }
}
