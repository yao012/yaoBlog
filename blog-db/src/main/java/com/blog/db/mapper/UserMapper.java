package com.blog.db.mapper;

import com.blog.db.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/24
 */
@Mapper
public interface UserMapper {
    /**
     * 通过username得到用户对象
     * @param name
     * @return
     */
    @Select("SELECT * FROM USERS WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    /**
     * 通过id得到用户对象
     * @param id
     * @return
     */
    @Select("SELECT * FROM USERS WHERE id = #{id}")
    User findById(@Param("id") String id);

    @Insert("insert into users(id,username,password,passwordMd5,email,regTime) VALUES(#{id},#{username},#{password},#{passwordMd5},#{email},#{regTime})")
    int insertUser(User user);

    @Update("update users set username = #{username} where id = #{id}")
    int updateUserName(@Param("id") String id,@Param("username") String username);

    @Select("select * from users ")
    List<User> listUser();


}
