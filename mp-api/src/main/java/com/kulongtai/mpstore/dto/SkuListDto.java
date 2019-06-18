package com.kulongtai.mpstore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2019/6/2 0002.
 */
@Data
public class SkuListDto extends PageDto {
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    @ApiModelProperty(value = "商品状态（1上架2下架）")
    private String skuStatus;
}
