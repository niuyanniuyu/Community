package com.community.service;

import com.community.dao.UserMapper;
import com.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //根据userId查询用户名
    public User findUserById(int id){
        return userMapper.selectById(id);
    }



}
