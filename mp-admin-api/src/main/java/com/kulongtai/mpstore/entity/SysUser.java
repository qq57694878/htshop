package com.kulongtai.mpstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysUser对象", description = "")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "登录名")
    @TableField("user_code")
    private String userCode;

    @ApiModelProperty(value = "姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
