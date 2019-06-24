package com.kulongtai.mpstore.vo;

import com.kulongtai.mpstore.entity.CardRecord;
import lombok.Data;

/**
 * Created by Administrator on 2019/6/24 0024.
 */
@Data
public class CardRecordVo extends CardRecord{
    private String catagory;
    private String cardName;
}
