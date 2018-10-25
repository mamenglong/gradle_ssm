/*
  Created by IntelliJ IDEA.
  User: Long
  Date: 2018/10/24
  Time: 11:30
  To change this template use File | Settings | File Templates.
*/
package org.controller;

import org.mybatis.entity.User;
import org.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController   {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/insert", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String insertUser() {
        User user = new User();
        user.setEmail("sssssasas");
        user.setPassword("44555");
        user.setSex("sss");
        userMapper.insert(user);
        return user.toString();
    }

    @RequestMapping(value = "/findAll",produces={"application/json; charset=UTF-8"})
    public List<User> findAll() {
        return userMapper.selectAll();
    }


}
