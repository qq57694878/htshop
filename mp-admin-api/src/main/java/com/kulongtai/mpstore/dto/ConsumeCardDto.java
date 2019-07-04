package com.kulongtai.mpstore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/6/10 0010.
 */
@Data
public class ConsumeCardDto implements Serializable {
    @ApiModelProperty(value = "卡券消费码")
    private Long cardNo;
    @ApiModelProperty(value = "本次消费次数")
    private Integer usedFrequency;
}
