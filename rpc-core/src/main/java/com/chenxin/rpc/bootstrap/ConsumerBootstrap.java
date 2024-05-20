package com.chenxin.rpc.bootstrap;

import com.chenxin.rpc.RpcApplication;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class ConsumerBootstrap {

    public static void init() {
        // Rpc框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
