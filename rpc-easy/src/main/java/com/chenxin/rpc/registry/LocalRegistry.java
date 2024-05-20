package com.chenxin.rpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fangchenxin
 * @description 本地注册中心
 * @date
 * @modify
 */
public class LocalRegistry {

    /**
     * 注册信息存储
     */
    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * @description: 注册服务
     * @param
     * @return:
     * @author: fangchenxin
     * @date: 2024/3/22 00:41
     * @modify:
     */
    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    /**
     * @description: 获取服务
     * @param
     * @return:
     * @author: fangchenxin
     * @date: 2024/3/22 00:41
     * @modify:
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * @description: 删除服务
     * @param
     * @return:
     * @author: fangchenxin
     * @date: 2024/3/22 00:42
     * @modify:
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
