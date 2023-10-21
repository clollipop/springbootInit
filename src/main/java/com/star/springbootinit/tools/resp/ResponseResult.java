package com.star.springbootinit.tools.resp;

import java.lang.annotation.*;

/**
 * @Auther: Star
 * @Description: 注释在方法类上，判断是否需要进行返回封装数据
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})  //作用于方法和类（接口）上
@Documented
public @interface ResponseResult {
}
