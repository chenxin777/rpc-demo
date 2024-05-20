package com.chenxin.rpc.config;

import com.chenxin.rpc.fault.retry.RetryStrategyKeys;
import com.chenxin.rpc.fault.tolerant.TolerantStrategyKeys;
import com.chenxin.rpc.loadbalancer.LoadBalancerKeys;
import com.chenxin.rpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * @author fangchenxin
 * @description RPC框架配置
 * @date
 * @modify
 */
@Data
public class RpcConfig {

    /**
     * 服务名称
     */
    private String name = "chen-rpc";

    /**
     * 服务版本
     */
    private String version = "1.0";

    /**
     * 服务主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务端口号
     */
    private Integer serverPort = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

    /**
     * 注册中心
     */
    private RegistryConfig registryConfig = new RegistryConfig();

    /**
     * 负载均衡器
     */
    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;

    /**
     * 重试策略
     */
    private String retryStrategy = RetryStrategyKeys.NO;

    /**
     * 容错策略
     */
    private String tolerantStrategy = TolerantStrategyKeys.FAIL_FAST;

}
