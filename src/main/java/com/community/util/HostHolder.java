package com.community.util;

import com.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息,代替Session对象
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    // 请求结束时清理线程中的User
    public void clear(){
        users.remove();
    }

}
