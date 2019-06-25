package com.kulongtai.mpstore.vo;

import com.kulongtai.mpstore.entity.CardRecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Lijinliang
 * @date 2019/6/25 15:25
 */
@Data
public class CardRecordListVo extends CardRecord{
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "卡名")
    private String cardName;

}
