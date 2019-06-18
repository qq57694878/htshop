package com.kulongtai.mpstore.common.exception;


import com.kulongtai.mpstore.common.constant.CommonConstants;

/**
 * Created by ace on 2017/9/8.
 */
public class UserTokenException extends BusinessException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_TOKEN);
    }
}
