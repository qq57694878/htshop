package com.kulongtai.mpstore.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.dto.CardRecordListDto;
import com.kulongtai.mpstore.entity.CardRecord;
import com.kulongtai.mpstore.mapper.CardRecordMapper;
import com.kulongtai.mpstore.service.ICardRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kulongtai.mpstore.vo.CardRecordListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户卡券消费记录表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-20
 */
@Service
public class CardRecordServiceImpl extends ServiceImpl<CardRecordMapper, CardRecord> implements ICardRecordService {
    @Autowired
    private CardRecordMapper cardRecordMapper;
    @Override
    public IPage<CardRecordListVo> pageCardRecordList(Page<Object> objectPage, CardRecordListDto cardRecordListDto) {
        return cardRecordMapper.pageCardRecordList(objectPage,cardRecordListDto);
    }
}
