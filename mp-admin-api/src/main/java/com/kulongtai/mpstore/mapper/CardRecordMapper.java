package com.kulongtai.mpstore.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.dto.CardListDto;
import com.kulongtai.mpstore.entity.CardRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kulongtai.mpstore.vo.CardListVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户卡券消费记录表 Mapper 接口
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-20
 */
public interface CardRecordMapper extends BaseMapper<CardRecord> {

 }
