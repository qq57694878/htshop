package com.kulongtai.mpstore.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 购物车
    * </p>
*
* @author lijinliang
* @since 2019-06-17
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_cart")
    @ApiModel(value="Cart对象", description="购物车")
    public class Cart extends Model<Cart> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "购物车id")
            @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

            @ApiModelProperty(value = "用户id")
        @TableField("user_id")
    private Integer userId;

            @ApiModelProperty(value = "商品id")
        @TableField("sku_id")
    private Integer skuId;

            @ApiModelProperty(value = "数量")
        @TableField("sku_num")
    private Integer skuNum;


    @Override
    protected Serializable pkVal() {
        return this.cartId;
    }

}
