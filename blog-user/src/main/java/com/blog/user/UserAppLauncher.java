package com.blog.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/24
 *
 * @MapperScan({"com.blog.db.*"})    指定mapper 扫描包的地址,否则 spring boot 将无法找到mapper地址
 * @ComponentScan({"com.blog.db.*"}) 指定其他组件扫描地址,除了mapper比较特殊外,其他组件依然使用组件扫描注解
 *
 */
@SpringBootApplication
@MapperScan({"com.blog.db.*"})
@ComponentScan({"com.blog.db.*","com.blog.user.*"})
public class UserAppLauncher {
    public static void main(String[] args) {
        SpringApplication.run(UserAppLauncher.class,args);
    }
}
