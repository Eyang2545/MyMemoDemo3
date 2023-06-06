package com.mymemo.demo.common;

public class ResultUtils {
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(ErrorCode.SUCCESS.getCode(),data,"成功",true ,ErrorCode.SUCCESS.getDescription());
    }
    public static <T> BaseResponse<T> success(T data,String message) {
        return new BaseResponse<>(ErrorCode.SUCCESS.getCode(),data,message,true ,ErrorCode.SUCCESS.getDescription());
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getCode(),null,"失败",false,errorCode.getDescription());
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode,String message) {
        return new BaseResponse<>(errorCode.getCode(),null,message,false,errorCode.getDescription());
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode,T data) {
        return new BaseResponse<>(errorCode.getCode(),data,"失败",false,errorCode.getDescription());
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode,String message,T data) {
        return new BaseResponse<>(errorCode.getCode(),data,message,false,errorCode.getDescription());
    }


}
