package com.kulongtai.mpstore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Lijinliang
 * @date 2019/6/24 11:45
 */
@Data
public class ModifyPasswordDto {
    @ApiModelProperty("原密码")
    private String password;
    @ApiModelProperty("新密码")
    private String newPassword;
}
