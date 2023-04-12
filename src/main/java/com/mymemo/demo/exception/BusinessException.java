package com.mymemo.demo.exception;


import com.mymemo.demo.common.ErrorCode;

public class BusinessException extends RuntimeException{

    private final String code;

    private final String description;

    private final ErrorCode errorCode;


    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
        this.errorCode=errorCode;
    }

    public String getCodeString() {return code;}

    public ErrorCode getErrorCode() {return errorCode;}

    public String getDescription() {return description;}

}
