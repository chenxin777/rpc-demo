package com.chenxin.rpc.registry;

import com.chenxin.rpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * @author fangchenxin
 * @description 注册中心服务本地缓存
 * @date
 * @modify
 */
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * @description 写缓存
     * @author fangchenxin
     * @date 2024/4/5 12:29
     * @param newServiceCache
     */
    void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    /**
     * @description 读缓存
     * @author fangchenxin
     * @date 2024/4/5 12:30
     * @return java.util.List<com.chenxin.rpc.model.ServiceMetaInfo>
     */
    List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }

    /**
     * @description 清空缓存
     * @author fangchenxin
     * @date 2024/4/5 12:30
     */
    void clearCache() {
        this.serviceCache = null;
    }




}
