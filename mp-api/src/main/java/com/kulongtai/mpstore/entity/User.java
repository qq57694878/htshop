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
    * 微信用户表
    * </p>
*
* @author lijinliang
* @since 2019-06-13
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("mp_user")
    @ApiModel(value="User对象", description="微信用户表")
    public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

            @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

            @ApiModelProperty(value = "用户名称")
        @TableField("username")
    private String username;

            @ApiModelProperty(value = "用户密码")
        @TableField("password")
    private String password;

            @ApiModelProperty(value = "性别：0 未知， 1男， 1 女")
        @TableField("gender")
    private Integer gender;

            @ApiModelProperty(value = "生日")
        @TableField("birthday")
    private Date birthday;

            @ApiModelProperty(value = "最近一次登录时间")
        @TableField("last_login_time")
    private Date lastLoginTime;

            @ApiModelProperty(value = "用户昵称或网络名称")
        @TableField("nickname")
    private String nickname;

            @ApiModelProperty(value = "用户手机号码")
        @TableField("mobile")
    private String mobile;

            @ApiModelProperty(value = "用户头像图片")
        @TableField("avatar")
    private String avatar;

            @ApiModelProperty(value = "微信登录openid")
        @TableField("openid")
    private String openid;

            @ApiModelProperty(value = "微信登录会话KEY")
        @TableField("session_key")
    private String sessionKey;

            @ApiModelProperty(value = "0 可用, 1 禁用, 2 注销")
        @TableField("status")
    private Integer status;

            @ApiModelProperty(value = "创建时间")
        @TableField("create_time")
    private Date createTime;

            @ApiModelProperty(value = "修改时间")
        @TableField("update_time")
    private Date updateTime;

            @ApiModelProperty(value = "逻辑删除")
        @TableField("del_flag")
    private Boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
