package com.blog.redis.service;

import com.blog.redis.config.FastJson2JsonRedisSerializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/25
 */
@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {

        FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer = new FastJson2JsonRedisSerializer<Object>(Object.class);

        // 更换key的序列化编码
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 更换value的序列化编码
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);

        ValueOperations<String, Object> vo =  redisTemplate.opsForValue();
        vo.set(key, value);
    }

    public Object get(String key) {
        ValueOperations<String, Object> vo =  redisTemplate.opsForValue();
        return vo.get(key);
    }


}
