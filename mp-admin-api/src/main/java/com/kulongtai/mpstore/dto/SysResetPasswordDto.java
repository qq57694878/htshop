package com.kulongtai.mpstore.dto;

import lombok.Data;

/**
 * @author Lijinliang
 * @date 2019/6/25 14:17
 */
@Data
public class SysResetPasswordDto {
    private String oldpassword;
    private String newpassword;
}
