package com.chenxin.rpc.server;

import io.vertx.core.Vertx;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class VertHttpServer implements HttpServer{

    @Override
    public void doStart(int port) {
        // 创建Vert.x实例
        Vertx vertx = Vertx.vertx();
        // 创建HTTP服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        // 监听端口并处理请求
        server.requestHandler(new HttpSeverHandler());
        // 启动HTTP服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start sever: " + result.cause());
            }
        });
    }
}
