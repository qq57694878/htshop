package com.kulongtai.mpstore.entity;

    import java.math.BigDecimal;
    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import java.util.Date;
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
    * 订单表
    * </p>
*
* @author lijinliang
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_order")
    @ApiModel(value="Order对象", description="订单表")
    public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "订单id")
            @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

            @ApiModelProperty(value = "商品id")
        @TableField("sku_id")
    private Integer skuId;

            @ApiModelProperty(value = "支付金额")
        @TableField("pay_price")
    private BigDecimal payPrice;

            @ApiModelProperty(value = "支付状态(0:未支付;1:已支付)")
        @TableField("pay_status")
    private String payStatus;

            @ApiModelProperty(value = "支付时间")
        @TableField("pay_time")
    private Date payTime;

            @ApiModelProperty(value = "用户id")
        @TableField("user_id")
    private Integer userId;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;

            @ApiModelProperty(value = "修改时间")
        @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

}
