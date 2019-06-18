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
    * 订单商品表
    * </p>
*
* @author lijinliang
* @since 2019-06-10
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_order_sku")
    @ApiModel(value="OrderSku对象", description="订单商品表")
    public class OrderSku extends Model<OrderSku> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "订单id")
        @TableField("order_id")
    private Integer orderId;

            @ApiModelProperty(value = "商品id")
        @TableField("sku_id")
    private Integer skuId;

            @ApiModelProperty(value = "购买数量")
        @TableField("buy_num")
    private Integer buyNum;

            @ApiModelProperty(value = "商品名称")
        @TableField("sku_name")
    private String skuName;

            @ApiModelProperty(value = "商品简述")
        @TableField("sku_desc")
    private String skuDesc;

            @ApiModelProperty(value = "商品价格")
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

            @ApiModelProperty(value = "商品主图片url")
        @TableField("main_url")
    private String mainUrl;

            @ApiModelProperty(value = "业务分类（1次数卡2E卡4套餐）")
        @TableField("buss_type")
    private String bussType;

            @ApiModelProperty(value = "次数")
        @TableField("frequency")
    private Integer frequency;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
