package com.star.springbootinit.exception;


import com.star.springbootinit.common.resp.ResultCode;

/**
 * @Auther: Star
 * @Description: 业务异常类
 * @Version: 1.0
 */
public class BizException extends BaseException{
    public BizException(ResultCode resultCode) {
        super(resultCode);
    }
}
