package com.star.springbootinit.config;

import com.star.springbootinit.exception.BaseException;
import com.star.springbootinit.common.resp.ErrorResult;
import com.star.springbootinit.common.resp.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.star.springbootinit.common.resp.ResultCode.PARAMETER_EXCEPTION;

/**
 * @Auther: Star
 * @Description: 全局统一异常处理
 * @Version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {
    /**
     * 通用异常类
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ErrorResult handleHttpException(HttpServletRequest req, BaseException e) {
        Map<String, String> reqInfo = getResponseInfo(req);
        log.error(e.getMessage() + "__" + reqInfo.get("method") + " " + reqInfo.get("url"), e);
        return ErrorResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 统一处理非自定义异常外的所有异常
     */
    @ExceptionHandler(Exception.class)
    public ErrorResult handleException(HttpServletRequest req, Exception e) {
        Map<String, String> reqInfo = getResponseInfo(req);
        log.error(e.getMessage() + "   " + reqInfo.get("method") + " " + reqInfo.get("url"), e);
        return ErrorResult.error(ResultCode.RC500);
    }

    /**
     * 兼容Validation校验框架：忽略参数异常处理器
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResult parameterMissingExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return ErrorResult.error(PARAMETER_EXCEPTION, "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 兼容Validation校验框架：缺少请求体异常处理器
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResult parameterBodyMissingExceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        return ErrorResult.error(PARAMETER_EXCEPTION, "参数体不能为空");
    }

    /**
     * 兼容Validation校验框架：参数效验异常处理器
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult parameterExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        return parameterException(e);
    }

    /**
     * 兼容Validation校验框架：参数效验异常处理器
     */
    @ExceptionHandler(BindException.class)
    public ErrorResult parameterBindExceptionHandler(HttpServletRequest req, BindException e) {
        return parameterException(e);
    }

    /**
     * 获取请求信息
     *
     * @param req
     * @return
     */
    private Map<String, String> getResponseInfo(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        map.put("url", req.getRequestURI());
        map.put("method", req.getMethod());
        return map;
    }

    /**
     * 格式化 多条报错信息
     *
     * @param errors
     * @return
     */
    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuilder msg = new StringBuilder();
        errors.forEach(error -> msg.append(error.getDefaultMessage()).append(";"));
        return msg.toString();
    }

    private ErrorResult parameterException(BindException e) {
        log.error(e.getMessage(), e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                // 但是在日志上面需要进行全部打印，方便快速更改
                String s = formatAllErrorMessages(errors);
                // 日志打印
                log.error("参数错误："+s);
                return ErrorResult.error(PARAMETER_EXCEPTION, fieldError.getDefaultMessage());
            }
        }
        return ErrorResult.error(PARAMETER_EXCEPTION, "请求参数校验异常");
    }
}
