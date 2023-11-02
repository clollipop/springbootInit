package com.star.springbootinit.config.saToken;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.star.springbootinit.common.resp.ResultCode;
import com.star.springbootinit.exception.BizException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * <p>
 * Sa-Token 权限认证 配置类
 * </p>
 *
 * @author: Star
 * @Description: com.star.springbootinit.config.saToken
 * @Version: 1.0
 */

@Log4j2
@Configuration
public class SaTokenInterceptor implements WebMvcConfigurer {

    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能 这样的话就可以进行注解校验了
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("sa-token拦截器");
        registry.addInterceptor(new SaInterceptor(handler -> {
                    // 指定一条 match 规则
                    SaRouter
                            .match("/**")    // 拦截的 path 列表，可以写多个 */
                            // 排除掉的 path 列表
                            // 排除指定url  获取的方法
                            .notMatch("/**/login/**", "/**/outLogin/**", "/**/error/**",
                                    "/**/register/**", "/**/verify/**", "/**/monitorLogin/**", "/**/enterprise/get",
                                    "/**/enterprise/getLogo", "/**/getSAStoken/**", "/**/favicon.ico")
                            .notMatch("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html")
                            .check(r -> {
                                        // 先判断是否登录
                                        try {
                                            StpUtil.checkLogin();
                                        } catch (NotLoginException e) {
                                            log.error(e.getMessage());
                                            throw new BizException(ResultCode.RC403);
                                        }
                                        // 查看token是否要过期
                                        long tokenSessionTimeout = StpUtil.getTokenTimeout();// 获取当前 Token 剩余有效时间 (单位: 秒)
                                        log.info("tokenSessionTimeout剩余时间{}:", tokenSessionTimeout);
                                        // 如果小于一天 那么就进行续期
                                        if (tokenSessionTimeout < (60 * 60 * 24)) {
                                            // 续期10天
                                            StpUtil.renewTimeout(60 * 60 * 24 * 10);
                                        }
                                    }
                            );        // 校验是否登录了
                    // 根据路由划分模块，不同模块不同鉴权
                    SaRouter.notMatch("/**/login/**","/**/outLogin/**").match("/user/**", r -> StpUtil.checkPermission("user"));
                    SaRouter.match("/role/**", r -> StpUtil.checkPermission("role"));
                    SaRouter.match("/func/**", r -> StpUtil.checkPermission("func"));
                }))
                .addPathPatterns("/**");
    }
}


