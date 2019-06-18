package com.kulongtai.mpstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2019/6/9 0009.
 */
@Data
public class CardListDto extends PageDto{
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "卡券消费码")
    private String cardNo;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "有效标记")
    private String validFlag;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
}
