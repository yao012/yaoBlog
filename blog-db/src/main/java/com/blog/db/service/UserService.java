package com.blog.db.service;

import com.blog.db.entity.User;
import com.blog.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/24
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(String id){
        return userMapper.findById(id);
    }


    public void insertUser(User user){
        userMapper.insertUser(user);
    }

}
