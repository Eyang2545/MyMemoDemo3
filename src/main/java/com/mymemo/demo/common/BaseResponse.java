package com.mymemo.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    @JsonIgnore
    private final long serialVersionUID=-2082203801443301445L;

    @JsonProperty("isSuccess")
    boolean success = false;
    private String code;

    private T data;

    private String message;

    private String description;

    public BaseResponse() {

    }

    public BaseResponse(String code, T data, String message, boolean success, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.success= success;
        this.description = description;
    }



}
