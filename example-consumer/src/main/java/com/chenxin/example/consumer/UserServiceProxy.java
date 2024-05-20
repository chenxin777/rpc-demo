package com.chenxin.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.chenxin.example.common.model.User;
import com.chenxin.example.common.service.UserService;
import com.chenxin.rpc.serializer.JdkSerializer;
import com.chenxin.rpc.model.RpcRequest;
import com.chenxin.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
@Slf4j
public class UserServiceProxy implements UserService {

    @Override
    public User getUser(User user) {
        // 指定序列化器
        JdkSerializer serializer = new JdkSerializer();
        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
