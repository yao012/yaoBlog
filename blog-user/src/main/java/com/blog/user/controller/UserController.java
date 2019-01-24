package com.blog.user.controller;

import com.blog.db.entity.User;
import com.blog.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/24
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "这是index";
    }

    @RequestMapping("test_insert")
    public String testInsert(){
        User user = new User();
        userService.insertUser(user);
        return "insert";
    }


}
