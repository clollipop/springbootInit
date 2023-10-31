package com.star.springbootinit.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.springbootinit.common.resp.R;
import com.star.springbootinit.common.resp.ResponseResult;
import com.star.springbootinit.exception.BaseException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Auther: Star
 * @Description:  全局统一返回 RestControllerAdvice 表示这是一个全局控制器增强类，用于处理 Controller 层的响应体
 * @Version: 1.0
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private ObjectMapper objectMapper;
    /* 使用统一返回体的标识 */
    private static final String RESPONSE_RESULT_ANNOTATION = "RESPONSE-RESULT-ANNOTATION";

    /**
     * 如果为 false 则不进行统一返回 则不知 beforeBodyWrite
     * @param methodParameter 方法参数
     * @param aClass 类
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(sra).getRequest();
        ResponseResult responseResult = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANNOTATION);
        // 判断返回体是否需要处理
        return responseResult != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 提供一定的灵活度，如果body已经被包装了，就不进行包装
        if (body instanceof R) {
            return body;
        }
        // 异常响应体则直接返回code+message的消息体
        if (body instanceof BaseException) {
            return body;
        }
        // 如果返回的是String类型 会进行转换错误，所以需要特意适配
        if (body instanceof String) {
            try {
                return this.objectMapper.writeValueAsString(R.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return R.success(body);
    }
}
