package com.blog.redis.controller;

import com.blog.redis.model.User;
import com.blog.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/25
 */
@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("set")
    public Object testSet(@RequestParam int id,@RequestParam String name ) throws ClassNotFoundException {
        User user = new User();
        user.setId(id);
        user.setName(name);
        redisService.set(Integer.toString(id),user);
        return "设置对象到redis 成功";
    }


    @RequestMapping("get")
    public Object testGet(@RequestParam int id ){
        Object obj = redisService.get(Integer.toString(id));
        User user = (User)obj;


        return user;
    }


}
