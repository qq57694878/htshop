package com.kulongtai.mpstore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Lijinliang
 * @date 2019/6/24 10:13
 */
@Data
public class CardListDto extends PageDto{
    @ApiModelProperty(value = "有效标记 1有效 0 无效")
    private String validFlag;
}
