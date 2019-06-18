package com.kulongtai.mpstore.entity;

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
    * 
    * </p>
*
* @author lijinliang
* @since 2019-06-11
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_page")
    @ApiModel(value="Page对象", description="")
    public class Page extends Model<Page> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "页面id")
            @TableId(value = "page_id", type = IdType.AUTO)
    private Integer pageId;

            @ApiModelProperty(value = "页面类型(10首页 20自定义页)")
        @TableField("page_type")
    private Integer pageType;

            @ApiModelProperty(value = "页面数据")
        @TableField("page_data")
    private String pageData;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;

            @ApiModelProperty(value = "修改时间")
        @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.pageId;
    }

}
