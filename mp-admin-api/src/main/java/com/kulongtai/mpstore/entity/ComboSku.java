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
    * 套餐商品绑定表
    * </p>
*
* @author lijinliang
* @since 2019-06-02
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_combo_sku")
    @ApiModel(value="ComboSku对象", description="套餐商品绑定表")
    public class ComboSku extends Model<ComboSku> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "套餐商品id")
        @TableField("combo_sku_id")
    private Integer comboSkuId;

            @ApiModelProperty(value = "商品id")
        @TableField("sku_id")
    private String skuId;

            @ApiModelProperty(value = "商品价格")
        @TableField("origin_price")
    private BigDecimal originPrice;

            @ApiModelProperty(value = "商品画线价格")
        @TableField("discount_price")
    private BigDecimal discountPrice;

            @ApiModelProperty(value = "数量")
        @TableField("sku_num")
    private Integer skuNum;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
