package com.blog.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/24
 */
@SpringBootApplication
@ComponentScan({"com.blog.db.*"})
public class DbAppLauncher {
    public static void main(String[] args) {
        SpringApplication.run(DbAppLauncher.class,args);
    }
}

