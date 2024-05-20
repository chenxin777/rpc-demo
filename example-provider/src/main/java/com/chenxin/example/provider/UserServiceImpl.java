package com.chenxin.example.provider;

import com.chenxin.example.common.model.User;
import com.chenxin.example.common.service.UserService;

/**
 * @author fangchenxin
 * @description 用户服务实现类
 * @date
 * @modify
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
