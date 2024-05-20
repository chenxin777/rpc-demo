package com.chenxin.rpc.server;

import com.chenxin.rpc.RpcApplication;
import com.chenxin.rpc.serializer.Serializer;
import com.chenxin.rpc.serializer.SerializerFactory;
import com.chenxin.rpc.model.RpcRequest;
import com.chenxin.rpc.model.RpcResponse;
import com.chenxin.rpc.registry.LocalRegistry;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author fangchenxin
 * @description Http请求处理
 * @date
 * @modify
 */
@Slf4j
public class HttpSeverHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        //final JdkSerializer serializer = new JdkSerializer();
        // 动态获取序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 记录日志
        log.info("Receive request: " + request.method() + " " + request.uri());
        // 异步处理HTTP请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            // 请求为null，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }
            try {
                // 获取要调用的服务实现类，通过反射调用
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.getDeclaredConstructor().newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                log.error(e.getMessage());
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request, rpcResponse, serializer);
        });
    }

    /**
     * @description: 响应
     * @param
     * @return:
     * @author: fangchenxin
     * @date: 2024/3/23 12:46
     * @modify:
     */
    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            log.error(e.getMessage());
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
