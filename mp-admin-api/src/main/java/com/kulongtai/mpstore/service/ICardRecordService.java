package com.kulongtai.mpstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.dto.CardRecordListDto;
import com.kulongtai.mpstore.entity.CardRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kulongtai.mpstore.vo.CardRecordListVo;

/**
 * <p>
 * 用户卡券消费记录表 服务类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-20
 */
public interface ICardRecordService extends IService<CardRecord> {

    IPage<CardRecordListVo> pageCardRecordList(Page<Object> objectPage, CardRecordListDto cardRecordListDto);
}
