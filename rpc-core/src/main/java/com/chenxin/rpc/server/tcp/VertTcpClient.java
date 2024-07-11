package com.chenxin.rpc.server.tcp;

import cn.hutool.core.util.IdUtil;
import com.chenxin.rpc.RpcApplication;
import com.chenxin.rpc.model.RpcRequest;
import com.chenxin.rpc.model.RpcResponse;
import com.chenxin.rpc.model.ServiceMetaInfo;
import com.chenxin.rpc.protocol.*;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class VertTcpClient {

    public void start() {
        // 创建Vert.x实例
        Vertx vertx = Vertx.vertx();
        vertx.createNetClient().connect(8888, "localhost", result -> {
            if (result.succeeded()) {
                System.out.println("Connected To TCP server");
                NetSocket socket = result.result();
                for (int i = 0; i < 1; i++) {
                    // 发送数据
                    Buffer buffer = Buffer.buffer();
                    String str = "hello fcx! hello fcx!";
                    // 4字节
                    buffer.appendInt(0);
                    // 信息体长度 4字节
                    buffer.appendInt(str.getBytes(StandardCharsets.UTF_8).length);
                    // 信息体
                    buffer.appendBytes(str.getBytes(StandardCharsets.UTF_8));
                    System.out.println("发送数据：" + buffer);
                    socket.write(buffer);
                }
                // 接收响应
                socket.handler(buffer -> {
                    System.out.println("Received response from server: " + buffer.toString());
                });
            } else {
                System.err.println("Failed to connect to TCP server");
            }
        });
    }

    public static RpcResponse doRequest(RpcRequest rpcRequest, ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException, TimeoutException {
        Vertx vertx = Vertx.vertx();
        NetClient netClient = vertx.createNetClient();
        CompletableFuture<RpcResponse> responseFuture = new CompletableFuture<>();
        netClient.connect(serviceMetaInfo.getServicePort(), serviceMetaInfo.getServiceHost(), result -> {
            if (result.succeeded()) {
                System.out.println("Connected to TCP server");
                NetSocket socket = result.result();
                // 发送数据
                // 构造消息
                ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
                ProtocolMessage.Header header = new ProtocolMessage.Header();
                header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
                header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
                header.setSerializer((byte) Objects.requireNonNull(ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer())).getKey());
                header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
                header.setRequestId(IdUtil.getSnowflakeNextId());
                protocolMessage.setBody(rpcRequest);
                protocolMessage.setHeader(header);
                // 进行编码
                try {
                    Buffer requestBuffer = ProtocolMessageEncoder.encoder(protocolMessage);
                    socket.write(requestBuffer);
                } catch (IOException ex) {
                    throw new RuntimeException("协议消息编码错误");
                }
                // 接受响应
                TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
                    try {
                        ProtocolMessage<RpcResponse> rpcResponseProtocolMessage = (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
                        responseFuture.complete(rpcResponseProtocolMessage.getBody());
                    } catch (IOException ex) {
                        throw new RuntimeException("协议消息解码错误");
                    }
                });
                socket.handler(bufferHandlerWrapper);
            } else {
                System.err.println("Failed to connect to TCP server");
            }
        });
        RpcResponse rpcResponse = responseFuture.get(2, TimeUnit.SECONDS);
        // 关闭连接
        netClient.close();
        return rpcResponse;
    }

    public static void main(String[] args) {
        new VertTcpClient().start();
    }
}
