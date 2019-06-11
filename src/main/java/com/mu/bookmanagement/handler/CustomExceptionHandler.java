package com.mu.bookmanagement.handler;

import com.mu.bookmanagement.entity.ResultEntity;
import com.mu.bookmanagement.entity.UserEntity;
import com.mu.bookmanagement.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException (Exception e) {
        e.printStackTrace();
        if (e instanceof SystemException) {
            SystemException exception = (SystemException) e;
            return new ResultEntity<UserEntity>(exception.getCode(),exception.getMessage(),null);
        } else {
            return new ResultEntity<Object>(-1,"未知错误",null);
        }
    }
}
