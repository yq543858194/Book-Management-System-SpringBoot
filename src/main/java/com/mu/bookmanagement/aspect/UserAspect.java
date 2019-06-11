package com.mu.bookmanagement.aspect;

import com.mu.bookmanagement.exception.SystemException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import com.mu.bookmanagement.enums.ExpectionEnum;

@Aspect
public class UserAspect {

    @Autowired
    HttpSession session;

    @Before("execution(public * com.mu.bookmanagement.controller.BookController.*(..))")
    public void validUser () {
        if (session.getAttribute("user") == null) {
            throw new SystemException(ExpectionEnum.USER_NOT_LOGIN.getCode(),ExpectionEnum.USER_NOT_LOGIN.getMsg());
        }
    }
}
