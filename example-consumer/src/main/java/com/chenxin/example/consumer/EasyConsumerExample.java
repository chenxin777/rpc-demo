package com.chenxin.example.consumer;

import com.chenxin.example.common.model.User;
import com.chenxin.example.common.service.UserService;
import com.chenxin.rpc.proxy.ServiceProxyFactory;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class EasyConsumerExample {

    public static void main(String[] args) {

        // 静态代理 获取UserService实现类对象
        // UserService userService = new UserServiceProxy();
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("chen");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
