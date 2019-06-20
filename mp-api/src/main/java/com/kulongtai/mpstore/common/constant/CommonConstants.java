package com.kulongtai.mpstore.common.constant;

/**
 * Created by ace on 2017/8/29.
 */
public class CommonConstants {
    // 用户token异常
    public static final Integer EX_USER_INVALID_TOKEN = 401;
    public static final Integer EX_OTHER_CODE = 500;
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER = "currentUser";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "name";

    /**
     * 游客统一用户id
     */
    public static final Integer VISITOR_USER_ID=-1;
}
