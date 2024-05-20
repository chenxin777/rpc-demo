package com.chenxin.rpc.serializer;

import java.io.IOException;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public interface Serializer {
    /**
     * @description 序列化
     * @author fangchenxin
     * @date 2024/3/22 22:06
     * @modify
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * @description: 反序列化
     * @param
     * @return:
     * @author: fangchenxin
     * @date: 2024/3/22 22:06
     * @modify:
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}


