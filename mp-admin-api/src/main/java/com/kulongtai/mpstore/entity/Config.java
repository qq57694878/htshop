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
    * 配置表
    * </p>
*
* @author lijinliang
* @since 2019-06-09
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_config")
    @ApiModel(value="Config对象", description="配置表")
    public class Config extends Model<Config> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "k")
        @TableField("k")
    private String k;

            @ApiModelProperty(value = "v")
        @TableField("v")
    private String v;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
