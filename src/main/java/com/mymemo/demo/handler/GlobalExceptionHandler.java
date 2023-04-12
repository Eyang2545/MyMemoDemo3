package com.mymemo.demo.handler;


import com.mymemo.demo.common.BaseResponse;
import com.mymemo.demo.common.ErrorCode;
import com.mymemo.demo.common.ResultUtils;
import com.mymemo.demo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("businessException" + e.getMessage(),e);
        return ResultUtils.error(e.getErrorCode(),e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR_B0001,e.getMessage(),"");
    }

}
