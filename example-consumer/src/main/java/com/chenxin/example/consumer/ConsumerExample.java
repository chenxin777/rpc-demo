package com.chenxin.example.consumer;

import com.chenxin.example.common.model.User;
import com.chenxin.example.common.service.UserService;
import com.chenxin.rpc.bootstrap.ConsumerBootstrap;
import com.chenxin.rpc.config.RpcConfig;
import com.chenxin.rpc.proxy.ServiceProxyFactory;
import com.chenxin.rpc.utils.ConfigUtils;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class ConsumerExample {

    public static void main(String[] args) {
        // 消费者初始化
        ConsumerBootstrap.init();
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("fcx");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
