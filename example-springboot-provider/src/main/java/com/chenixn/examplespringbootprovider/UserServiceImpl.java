package com.chenixn.examplespringbootprovider;

import com.chenxin.example.common.model.User;
import com.chenxin.example.common.service.UserService;
import com.chenxin.rpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * @author fangchenxin
 * @description 用户服务实现类
 * @date
 * @modify
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
