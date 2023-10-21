package com.star.springbootinit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ExceptionCodeConfiguration
 * @Author Star
 * @Description 异常 响应码 对应 描述获取类   一般用于自己抛出去的异常 才回去获取  参数异常已经进行封装所以没必要进行使用 项目中并没有用 可自我选择
 */
@ConfigurationProperties(prefix = "star")
@PropertySource("classpath:config/exception-code.yml")
@Component
public class ExceptionCodeConfiguration {
    // 名字和配置文件进行绑定
    private Map<Integer, String> codes = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return codes;
    }

    /**
     * 获取code 对应信息
     * @param code
     * @return code 对应描述
     */
    public String getMessage(Integer code){
        return codes.get(code);
    }
}
