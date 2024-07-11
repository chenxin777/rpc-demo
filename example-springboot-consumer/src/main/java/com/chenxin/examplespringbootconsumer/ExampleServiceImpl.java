package com.chenxin.examplespringbootconsumer;

import com.chenxin.example.common.model.User;
import com.chenxin.example.common.service.UserService;
import com.chenxin.rpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public String test() {
        User user = new User();
        user.setName("fcx");
        User result = userService.getUser(user);
        return result.getName();
    }
}
