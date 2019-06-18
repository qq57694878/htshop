package com.kulongtai.mpstore.vo;

import com.kulongtai.mpstore.entity.Sku;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2019/6/18 0018.
 */
@Data
public class CartListVo extends Sku{
    @Autowired
    private Integer skuNum;
}
