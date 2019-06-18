package com.kulongtai.mpstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2019/6/2 0002.
 */
@Data
public class OrderListDto extends PageDto {
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "订单状态（0待付款;1已取消;8已完成）")
    private String orderStatus;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户电话")
    private String mobile;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
}
