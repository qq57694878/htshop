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
    * 用户表
    * </p>
*
* @author lijinliang
* @since 2019-06-20
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_user")
    @ApiModel(value="User对象", description="用户表")
    public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "用户id")
            @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

            @ApiModelProperty(value = "电话")
        @TableField("mobile")
    private String mobile;

            @ApiModelProperty(value = "密码")
        @TableField("password")
    private String password;

            @ApiModelProperty(value = "车牌号")
        @TableField("car_no")
    private String carNo;

            @ApiModelProperty(value = "昵称")
        @TableField("nickname")
    private String nickname;

            @ApiModelProperty(value = "头像")
        @TableField("avatar")
    private String avatar;

            @ApiModelProperty(value = "openid")
        @TableField("openid")
    private String openid;

            @ApiModelProperty(value = "session_key")
        @TableField("session_key")
    private String sessionKey;

            @ApiModelProperty(value = "最后登录时间")
        @TableField("last_login_time")
    private Date lastLoginTime;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;

            @ApiModelProperty(value = "修改时间")
        @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
