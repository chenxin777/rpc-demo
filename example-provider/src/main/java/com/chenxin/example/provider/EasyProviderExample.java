package com.chenxin.example.provider;

import com.chenxin.example.common.service.UserService;
import com.chenxin.rpc.registry.LocalRegistry;
import com.chenxin.rpc.server.VertHttpServer;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class EasyProviderExample {

    public static void main(String[] args) {

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 提供服务
        VertHttpServer httpServer = new VertHttpServer();
        httpServer.doStart(8080);
    }
}
