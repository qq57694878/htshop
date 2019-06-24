package com.kulongtai.mpstore.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.dto.CardListDto;
import com.kulongtai.mpstore.entity.Card;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kulongtai.mpstore.vo.CardListVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户卡券表 Mapper 接口
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-20
 */
public interface CardMapper extends BaseMapper<Card> {

    IPage<CardListVo> pageCardList(Page<Object> objectPage, @Param("p") CardListDto cardListDto);

}
