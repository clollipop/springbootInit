package com.star.springbootinit.tools.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Star
 * @Description: 异常统一返回类
 * @Version: 1.0
 */
@Data
public class ErrorResult implements Serializable {
    private Integer code;
    private String message;
    private boolean success = false;
    @JsonIgnore
    private ResultCode resultCode;

    private ErrorResult() {
    }

    public static ErrorResult error(ResultCode resultCode) {
        ErrorResult result = new ErrorResult();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    /**
     * 最基础版本 默认500 和 系统异常
     * @return
     */
    public static ErrorResult error() {
        return error(ResultCode.RC500);
    }

    /**
     * 默认500 和 自定义 返回信息
     * @param message
     * @return
     */
    public static ErrorResult error(String message) {
        ErrorResult result = new ErrorResult();
        result.setCode(ResultCode.RC500.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 最常用 根据code 去找到对应的消息并返回
     * @param code
     * @return
     */
    public static ErrorResult error(Integer code) {
        return error(ResultCode.findEnumCode(code));
    }

    /**
     * 自定义code 和 消息
     * @param code
     * @param message
     * @return
     */
    public static ErrorResult error(Integer code, String message) {
        ErrorResult result = new ErrorResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 根据枚举去获取里面的code 但消息自定义
     * @param resultCode
     * @param message
     * @return
     */
    public static ErrorResult error(ResultCode resultCode, String message) {
        ErrorResult result = error(resultCode);
        result.setMessage(message);
        return result;
    }
}

