package com.blog.db.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: yaoZhenGuo
 * @date: 2019/01/24
 *
 * @Data 注解相当于 自动实现了get set 方法,使当前类拥有toString,无参构造函数
 *          @ToString、@Getter、@Setter、@EqualsAndHashCode、@NoArgsConstructor
 *
 */
@Data
public class User {

    private String id;

    private String username;

    private String password;

    private String passwordMd5;

    private String email;

    private Date regTime;

}
