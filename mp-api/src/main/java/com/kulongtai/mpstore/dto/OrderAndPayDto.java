package com.kulongtai.mpstore.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author Lijinliang
 * @date 2019/6/20 11:11
 */
@Data
public class OrderAndPayDto implements Serializable {
    @Autowired
    private Integer skuId;
}
