package com.kulongtai.mpstore.common.exception;

import com.kulongtai.mpstore.common.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@ResponseBody
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    public R handler(Exception e){
        logger.info("[系统异常] {}",e.getMessage());
        return new R(500,e.getMessage());
    }
    @ExceptionHandler(BussinessException.class)
    public R baseExceptionHandler(HttpServletResponse response, BussinessException ex) {
        logger.error(ex.getMessage(),ex);
        return new R(ex.getStatus(), ex.getMessage());
    }
    @ExceptionHandler(UserTokenException.class)
    public R userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        logger.error(ex.getMessage(),ex);
        return new R(ex.getStatus(), ex.getMessage());
    }
}