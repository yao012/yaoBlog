package com.blog.db.service;

import com.blog.db.entity.User;
import com.blog.db.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void updateUserName(String id,String username){
        userMapper.updateUserName(id,username);
    }

    public PageInfo<User> listUser(int page, int size){
        PageHelper.startPage(page,size);
        List<User> userList = userMapper.listUser();
        PageInfo<User> pageInfoUser = new PageInfo<User>(userList);
        return pageInfoUser;
    }

}
