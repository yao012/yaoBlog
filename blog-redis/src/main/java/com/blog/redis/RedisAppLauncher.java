package com.blog.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/25
 */
@SpringBootApplication
public class RedisAppLauncher {

    public static void main(String[] args) {
        SpringApplication.run(RedisAppLauncher.class,args);
    }

//    @Bean
//    public RedisSerializer fastJson2JsonRedisSerializer() {
//        return new FastJson2JsonRedisSerializer<Object>(Object.class);
//    }

}
