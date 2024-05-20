package com.chenxin.rpc.registry;

import com.chenxin.rpc.config.RegistryConfig;
import com.chenxin.rpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * @author fangchenxin
 * @description 注册中心
 * @date
 * @modify
 */
public interface Registry {

    /**
     * @description 初始化
     * @author fangchenxin
     * @date 2024/4/4 00:19
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * @description 注册服务（服务端）
     * @author fangchenxin
     * @date 2024/4/4 00:20
     * @param serviceMetaInfo
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * @description 注销服务（服务端）
     * @author fangchenxin
     * @date 2024/4/4 00:21
     * @param serviceMetaInfo
     */
    void unregister(ServiceMetaInfo serviceMetaInfo);

    /**
     * @description 服务发现（获取某服务的所有节点，消费端）
     * @author fangchenxin
     * @date 2024/4/4 00:22
     * @param serviceKey
     * @return java.util.List<com.chenxin.rpc.model.ServiceMetaInfo>
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * @description 服务注销
     * @author fangchenxin
     * @date 2024/4/4 00:22
     */
    void destroy();

    /**
     * @description 心跳检测（服务端）
     * @author fangchenxin
     * @date 2024/4/5 00:24
     */
    void heartBeat();

    /**
     * @description 监听（消费端）
     * @author fangchenxin
     * @date 2024/4/5 15:54
     * @param serviceNodeKey
     */
    void watch(String serviceNodeKey);
}
