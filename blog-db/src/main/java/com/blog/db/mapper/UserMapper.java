package com.blog.db.mapper;

import com.blog.db.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/24
 */
@Component
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

    @Insert("INSERT INTO USERS(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);


    @Insert("INSERT INTO USERS VALUES(#{id},#{username},#{password},#{passwordMd5},#{email},#{regTime})")
    int insertUser(@Param("name") User user);

}
