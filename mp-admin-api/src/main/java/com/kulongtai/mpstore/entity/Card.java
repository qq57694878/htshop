package com.kulongtai.mpstore.entity;

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
    * 用户卡券表
    * </p>
*
* @author lijinliang
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_card")
    @ApiModel(value="Card对象", description="用户卡券表")
    public class Card extends Model<Card> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "我的卡券id")
            @TableId(value = "card_id", type = IdType.AUTO)
    private Integer cardId;

            @ApiModelProperty(value = "卡券消费码")
        @TableField("card_no")
    private Long cardNo;

            @ApiModelProperty(value = "用户id")
        @TableField("user_id")
    private Integer userId;

            @ApiModelProperty(value = "商品id")
        @TableField("sku_id")
    private String skuId;

            @ApiModelProperty(value = "卡券名称")
        @TableField("card_name")
    private String cardName;

            @ApiModelProperty(value = "总次数")
        @TableField("total_frequency")
    private Integer totalFrequency;

            @ApiModelProperty(value = "剩余次数")
        @TableField("rest_frequency")
    private Integer restFrequency;

            @ApiModelProperty(value = "卡片详情")
        @TableField("card_content")
    private String cardContent;

            @ApiModelProperty(value = "有效标记（1有效0无效）")
        @TableField("valid_flag")
    private String validFlag;

    @ApiModelProperty(value = "1:洗车2保养")
    @TableField("catagory")
    private String catagory;
            @ApiModelProperty(value = "订单id")
        @TableField("order_id")
    private Integer orderId;

            @ApiModelProperty(value = "有效期至")
        @TableField("valide_time")
    private Date valideTime;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;

            @ApiModelProperty(value = "修改时间")
        @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.cardId;
    }

}
