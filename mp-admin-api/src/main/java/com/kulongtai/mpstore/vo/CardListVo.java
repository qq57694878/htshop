package com.kulongtai.mpstore.vo;

import com.kulongtai.mpstore.entity.Card;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Lijinliang
 * @date 2019/6/24 15:18
 */
public class CardListVo extends Card {
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "车牌号")
    private String carNo;

}
