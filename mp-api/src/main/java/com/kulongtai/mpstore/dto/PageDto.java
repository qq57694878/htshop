package com.kulongtai.mpstore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/6/2 0002.
 */
@Data
public class PageDto implements Serializable {
    @ApiModelProperty(value = "当前页数")
    private long current=1;
    @ApiModelProperty(value = "每页记录数")
    private long size=10;
}
