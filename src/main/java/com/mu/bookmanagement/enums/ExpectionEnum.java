package com.mu.bookmanagement.enums;

import org.omg.PortableInterceptor.SUCCESSFUL;

public enum ExpectionEnum {
    USER_UNVAILD(2,"用户名或密码错误"),
    USER_EXISTS(3,"用户名已存在"),
    USER_NOT_LOGIN(4,"用户未登录"),
    USER_NOT_EXISTS(5,"用户不存在"),
    BOOK_NOT_EXISTS(6,"图书不存在"),
    SEARCH_TYPE_NOT_VAILD(7,"搜索类型非法"),
    SUCCESS(0,"");


    private Integer code;

    private String msg;

    ExpectionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
