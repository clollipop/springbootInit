package com.star.springbootinit.exception;

import com.star.springbootinit.common.resp.ResultCode;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author: Star
 * @Description: com.star.springbootinit.exception
 * @Version: 1.0
 */
@Data
public class BaseException extends RuntimeException{
    private static final int RC500_CODE = ResultCode.RC500.getCode();
    private static final String RC500_MASSAGE = ResultCode.RC500.getMessage();

    // 项目响应码
    private Integer code;
    // 前端响应码
    private String message;

    public BaseException() {
        super(RC500_MASSAGE);
        this.code = RC500_CODE;
        this.message = RC500_MASSAGE;
    }

    public BaseException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.code = RC500_CODE;
        this.message = RC500_MASSAGE;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = RC500_CODE;
        this.message = message;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
