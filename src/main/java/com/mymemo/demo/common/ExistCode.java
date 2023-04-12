package com.mymemo.demo.common;

public enum ExistCode {
    USERNAME(0,"username已存在"),
    PHONE(1,"phone已存在"),
    EMAIL(2,"email已存在");

    ExistCode(int code,String message){
    }

}
