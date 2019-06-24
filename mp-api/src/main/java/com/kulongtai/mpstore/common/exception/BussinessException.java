package com.kulongtai.mpstore.common.exception;

/**
 * Created by ace on 2017/9/8.
 */
public class BussinessException extends RuntimeException {
    private int status = 500;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BussinessException() {
    }

    public BussinessException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BussinessException(Throwable cause) {
        super(cause);
    }

    public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
