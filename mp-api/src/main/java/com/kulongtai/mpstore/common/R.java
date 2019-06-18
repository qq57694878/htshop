package com.kulongtai.mpstore.common;



import lombok.Data;

import java.io.Serializable;

/**
 * api 返回结果
 */
@Data
public class R<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public R() {
        this.code = 200;
        this.msg="";
    }


    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(T data) {
        this.code = 200;
        this.msg="";
        this.data = data;
    }
    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
 
}
