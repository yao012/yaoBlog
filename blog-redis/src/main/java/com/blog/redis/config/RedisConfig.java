package com.blog.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/25
 *
 * @EnableCaching  //启用缓存，这个注解很重要；
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    public CacheManager cacheManager(RedisConnectionFactory factory){
        /**
         * Obtains a Duration representing a number of seconds.
         * The nanosecond in second field is set to zero.
         * 获取表示秒数的持续时间。
         * 第二场中的纳秒设置为零。
         */
        Duration ofSecond = Duration.ofSeconds(1);
        /**
         * 获取默认的redis 缓存配置
         */
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig();
        /**
         * Set the ofSecond to apply for cache entries. Use {@link Duration#ZERO} to declare an eternal cache.
         * 将ofSecond 应用缓存条目,使用Duration 声明一个永久缓存
         */
        RedisCacheConfiguration config =  defaultConfig.entryTtl(ofSecond);
        /**
         * 使用{@link config} 创建一个 RedisCacheManager
         */
        RedisCacheManager cacheManager =  RedisCacheManager.builder(factory).cacheDefaults(config).build();

        return cacheManager;

    }

}
