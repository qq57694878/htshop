package com.kulongtai.mpstore.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/6/17 0017.
 */
@Data
public class CartAddDto implements Serializable {
    private Integer skuId;
    private Integer skuNum;
}
