package com.chenxin.rpc.server;

/**
 * @author fangchenxin
 * @description HTTP服务器接口
 * @date
 * @modify
 */
public interface HttpServer {

    /**
     * @description 启动服务器
     * @author fangchenxin
     * @date 2024/3/21 23:44
     * @modify
     */
    void doStart(int port);
}
