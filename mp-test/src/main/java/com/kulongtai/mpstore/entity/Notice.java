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
    * 通知公告表
    * </p>
*
* @author lijinliang
* @since 2019-06-19
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_notice")
    @ApiModel(value="Notice对象", description="通知公告表")
    public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "公告内容")
        @TableField("content")
    private String content;

            @ApiModelProperty(value = "开始时间")
        @TableField("start_time")
    private Date startTime;

            @ApiModelProperty(value = "结束时间")
        @TableField("end_time")
    private Date endTime;

            @ApiModelProperty(value = "删除标记")
        @TableField("del_flag")
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
