package com.kulongtai.mpstore.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lijinliang
 * @date 2019/6/20 9:32
 */
@Data
public class LoginDto implements Serializable {
    private String code;
    private String mobile;
    private String password;
}
