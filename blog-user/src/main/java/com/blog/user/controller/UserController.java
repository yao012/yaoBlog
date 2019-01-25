package com.blog.user.controller;

import com.alibaba.fastjson.JSON;
import com.blog.db.entity.User;
import com.blog.db.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

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

    @Transactional
    @RequestMapping("test_insert")
    public String testInsert(){

        User user = new User();
        User user1 = new User();
        try {

            user.setId(UUID.randomUUID().toString());
            user.setRegTime(new Date());
            user.setPassword("123456");
            user.setPasswordMd5("123456");
            user.setUsername("yao111");
            user.setEmail("121@qq.com");
            // 先插入正常user
            userService.insertUser(user);
            // 插入非正常user
            userService.insertUser(user1);
        }catch (Exception ex){
            // 一旦catch 到异常,如果希望实现事务回滚,就必须显式的执行下面这一句话
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
//        User user;
//        for(int i = 0;i<1000;i++){
//            user = new User();
//            user.setId(UUID.randomUUID().toString());
//            user.setRegTime(new Date());
//            user.setPassword("123456");
//            user.setPasswordMd5("123456");
//            user.setUsername("yao"+i);
//            user.setEmail("121@qq.com");
//            userService.insertUser(user);
//        }
        return "测试插入(有事务管理)";
    }

    /**
     * 一旦设置了rollbackFor = Exception.class  就不能再catch异常或者catch后显式的调用回滚操作
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("test_insert2")
    public String testInsertV2(){

        User user = new User();
        User user1 = new User();

        user.setId(UUID.randomUUID().toString());
        user.setRegTime(new Date());
        user.setPassword("123456");
        user.setPasswordMd5("123456");
        user.setUsername("yao111");
        user.setEmail("121@qq.com");
        try {
            // 先插入正常user
            userService.insertUser(user);
            // 插入非正常user
            userService.insertUser(user1);
        }catch (Exception e){
            // 手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return "测试插入rollbackFor (有事务管理)";
    }


    @RequestMapping("list_user")
    public PageInfo<User> listUser(int page, int size){
        return userService.listUser(page, size);
    }


    @RequestMapping("update")
    public String testUpdate(@RequestParam String id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String password){

        userService.updateUserName(id,name);
        return JSON.toJSONString(userService.getUserById(id));
    }


    public static void main(String[] args) {

        System.out.println(UUID.randomUUID().toString());


    }

}
