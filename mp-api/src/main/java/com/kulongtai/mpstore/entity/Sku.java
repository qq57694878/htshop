package com.kulongtai.mpstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* <p>
    * 卡券信息表
    * </p>
*
* @author lijinliang
* @since 2019-06-19
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_sku")
    @ApiModel(value="Sku对象", description="卡券信息表")
    public class Sku extends Model<Sku> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "卡券id")
            @TableId(value = "sku_id", type = IdType.AUTO)
    private Integer skuId;

            @ApiModelProperty(value = "卡券名称")
        @TableField("sku_name")
    private String skuName;

            @ApiModelProperty(value = "卡券售格")
        @TableField("sku_price")
    private BigDecimal skuPrice;

            @ApiModelProperty(value = "次数")
        @TableField("frequency")
    private Integer frequency;

            @ApiModelProperty(value = "卡券详情")
        @TableField("sku_content")
    private String skuContent;

            @ApiModelProperty(value = "排序(越小越靠前)")
        @TableField("sort")
    private Integer sort;

            @ApiModelProperty(value = "禁售类卡券标记1禁售 0 非禁售")
        @TableField("is_no_sale")
    private String isNoSale;

            @ApiModelProperty(value = "商品状态（1上架2下架）")
        @TableField("sku_status")
    private String skuStatus;

            @ApiModelProperty(value = "1:洗车2保养")
        @TableField("catagory")
    private String catagory;

            @ApiModelProperty(value = "用户购买后多少个月内使用有效")
        @TableField("valid_month")
    private Integer validMonth;

            @ApiModelProperty(value = "删除标记")
        @TableField("del_flag")
    private String delFlag;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;

            @ApiModelProperty(value = "修改时间")
        @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.skuId;
    }

}
