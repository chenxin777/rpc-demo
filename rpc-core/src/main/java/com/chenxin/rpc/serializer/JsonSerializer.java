package com.chenxin.rpc.serializer;

import com.chenxin.rpc.model.RpcRequest;
import com.chenxin.rpc.model.RpcResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author fangchenxin
 * @description JSON序列化器
 * @date
 * @modify
 */
public class JsonSerializer implements Serializer{

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        return objectMapper.writeValueAsBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        T obj = objectMapper.readValue(bytes, type);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest) obj, type);
        }
        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse) obj, type);
        }
        return obj;
    }

    /**
     * @description 由于Object原始对象会被擦除，导致反序列化时会被作为LinkedHashMap无法转换成原始对象
     * @author fangchenxin
     * @date 2024/4/1 21:56
     * @param rpcRequest
     * @param type
     * @return T
     */
    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> type) throws IOException{
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();
        // 循环处理每个参数的类型
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            // 如果类型不同，则重新处理一下类型
            // 判断parameterType是不是args的父类
            if (!parameterType.isAssignableFrom(args[i].getClass())) {
                // 不是的话 就将参数写成byte数组
                byte[] argBytes = objectMapper.writeValueAsBytes(args[i]);
                // 重新将参数类型映射为parameterType
                args[i] = objectMapper.readValue(argBytes, parameterType);
            }
        }
        return type.cast(rpcRequest);
    }

    /**
     * @description 由于Object原始对象会被擦除，导致反序列化时会被作为LinkedHashMap无法转换成原始对象
     * @author fangchenxin
     * @date 2024/4/1 21:59
     * @param rpcResponse
     * @param type
     * @return T
     */
    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> type) throws IOException{
        // 先将响应数据写入byte数组
        byte[] dataBytes = objectMapper.writeValueAsBytes(rpcResponse.getData());
        // 将byte数组映射为响应数据类型，重新set到rpcResponse
        rpcResponse.setData(objectMapper.readValue(dataBytes, rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }
}
