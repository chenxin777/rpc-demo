package com.chenxin.rpc.loadbalancer;

import com.chenxin.rpc.spi.SpiLoader;

/**
 * @author fangchenxin
 * @description 负载均衡器工厂
 * @date
 * @modify
 */
public class LoadBalancerFactory {

    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 默认负载均衡器（轮询）
     */
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    /**
     * @description 获取实例
     * @author fangchenxin
     * @date 2024/4/8 16:56
     * @param key
     * @return com.chenxin.rpc.loadbalancer.LoadBalancer
     */
    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }
}
