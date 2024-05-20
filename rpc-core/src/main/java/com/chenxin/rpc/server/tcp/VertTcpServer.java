package com.chenxin.rpc.server.tcp;

import com.chenxin.rpc.server.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;

import java.nio.charset.StandardCharsets;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class VertTcpServer implements HttpServer {

    @Override
    public void doStart(int port) {
        // 创建vert.x实例
        Vertx vertx = Vertx.vertx();
        // 创建tcp服务器
        NetServer server = vertx.createNetServer();
        // 处理请求
//        server.connectHandler(socket -> {
//            // 构造parser
//            RecordParser parser = RecordParser.newFixed(8);
//            parser.setOutput(new Handler<Buffer>() {
//
//                // 初始化
//                int size = -1;
//                // 一次完整的读取（头 + 体）
//                Buffer resultBuffer = Buffer.buffer();
//
//                @Override
//                public void handle(Buffer buffer) {
//                    if (-1 == size) {
//                        size = buffer.getInt(4);
//                        parser.fixedSizeMode(size);
//                        // 写入头信息到结果
//                        resultBuffer.appendBuffer(buffer);
//                    } else {
//                        // 写入体信息到结果
//                        resultBuffer.appendBuffer(buffer);
//                        System.out.println(resultBuffer.toString());
//                        // 重置一轮
//                        parser.fixedSizeMode(8);
//                        size = -1;
//                        resultBuffer = Buffer.buffer();
//                    }
//                }
//            });
//            socket.handler(parser);
//        });

        server.connectHandler(new TcpServerHandler());

        // 启动TCP服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("TCP server started on port " + port);
            } else {
                System.out.println("Failed to start TCP server: " + result.cause());
            }
        });
    }

    private byte[] handleRequest(byte[] requestData) {
        return "hello,client".getBytes(StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        new VertTcpServer().doStart(8888);
    }

}
