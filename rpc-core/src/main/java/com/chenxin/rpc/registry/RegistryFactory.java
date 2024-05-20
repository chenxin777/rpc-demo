package com.chenxin.rpc.registry;

import com.chenxin.rpc.spi.SpiLoader;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * @description 获取实例
     * @author fangchenxin
     * @date 2024/4/4 17:11
     * @param key
     * @return com.chenxin.rpc.registry.Registry
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }


}
