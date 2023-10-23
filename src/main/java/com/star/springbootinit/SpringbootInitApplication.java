package com.star.springbootinit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
// 这个注解使得Servlet可以被Spring Boot应用程序管理
@ServletComponentScan
@ComponentScan("com.star.springbootinit.*")
@MapperScan("com.star.springbootinit.mapper")
// 这个注解用于启用Spring的异步处理功能。它允许应用程序中的方法异步执行，以提高性能和并发处理能力。
@EnableAsync
public class SpringbootInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootInitApplication.class, args);
    }

}
