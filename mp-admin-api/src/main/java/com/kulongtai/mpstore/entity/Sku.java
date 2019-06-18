package com.kulongtai.mpstore.entity;

    import java.math.BigDecimal;
    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import java.util.Date;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 商品信息表
    * </p>
*
* @author lijinliang
* @since 2019-06-02
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_sku")
    @ApiModel(value="Sku对象", description="商品信息表")
    public class Sku extends Model<Sku> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "商品id")
            @TableId(value = "sku_id", type = IdType.AUTO)
    private Integer skuId;

            @ApiModelProperty(value = "商品名称")
        @TableField("sku_name")
    private String skuName;

            @ApiModelProperty(value = "商品简述")
        @TableField("sku_desc")
    private String skuDesc;

            @ApiModelProperty(value = "商品销售价格")
        @TableField("sku_price")
    private BigDecimal skuPrice;

            @ApiModelProperty(value = "商品面值")
        @TableField("face_price")
    private BigDecimal facePrice;

            @ApiModelProperty(value = "商品画线价格")
        @TableField("line_price")
    private BigDecimal linePrice;

            @ApiModelProperty(value = "商品详情")
        @TableField("sku_content")
    private String skuContent;

            @ApiModelProperty(value = "初始销售数")
        @TableField("sales_init")
    private Integer salesInit;

            @ApiModelProperty(value = "销售数量=初始销售数量+实际销售数量")
        @TableField("sales_num")
    private Integer salesNum;

            @ApiModelProperty(value = "排序(越小越靠前)")
        @TableField("sort")
    private Integer sort;

            @ApiModelProperty(value = "商品主图片url")
        @TableField("main_url")
    private String mainUrl;

            @ApiModelProperty(value = "商品状态（1上架2下架）")
    @TableField("sku_status")
    private String skuStatus;

            @ApiModelProperty(value = "商品分类id")
        @TableField("catagory_id")
    private Integer catagoryId;

            @ApiModelProperty(value = "删除标记")
        @TableField("del_flag")
    private String delFlag;

            @ApiModelProperty(value = "业务分类（1次数卡2E卡4套餐）")
        @TableField("buss_type")
    private String bussType;
    @ApiModelProperty(value = "次数")
    @TableField("frequency")
    private Integer frequency;

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
