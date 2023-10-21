package com.star.springbootinit.exception;

import com.star.springbootinit.tools.resp.ResultCode;

/**
 * @Auther: Star
 * @Description: 参数异常类
 * @Version: 1.0
 */
public class ParamException extends BaseException{
    public ParamException(ResultCode resultCode) {
        super(resultCode);
    }
}
