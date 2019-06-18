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
    * 用户卡券消费记录表
    * </p>
*
* @author lijinliang
* @since 2019-06-10
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_card_record")
    @ApiModel(value="CardRecord对象", description="用户卡券消费记录表")
    public class CardRecord extends Model<CardRecord> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "卡券id")
        @TableField("card_id")
    private Integer cardId;

            @ApiModelProperty(value = "卡券消费码")
        @TableField("card_no")
    private Integer cardNo;

            @ApiModelProperty(value = "用户id")
        @TableField("user_id")
    private Integer userId;

            @ApiModelProperty(value = "消费前余额")
        @TableField("before_used_price")
    private BigDecimal beforeUsedPrice;

            @ApiModelProperty(value = "消费金额")
        @TableField("used_price")
    private BigDecimal usedPrice;

            @ApiModelProperty(value = "消费后余额")
        @TableField("after_used_price")
    private BigDecimal afterUsedPrice;

            @ApiModelProperty(value = "业务分类（1次数卡2E卡）")
        @TableField("buss_type")
    private String bussType;

            @ApiModelProperty(value = "本次消费次数")
        @TableField("used_frequency")
    private Integer usedFrequency;

            @ApiModelProperty(value = "消费后剩余次数")
        @TableField("after_used_frequency")
    private Integer afterUsedFrequency;

            @ApiModelProperty(value = "消费前次数")
        @TableField("before_used_frequency")
    private Integer beforeUsedFrequency;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
