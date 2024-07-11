package com.chenxin.rpc.registry;

import com.chenxin.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fangchenxin
 * @description 注册中心服务本地缓存
 * @date
 * @modify
 */
public class RegistryServiceCache {

    /**
     * key: 服务名+版本号（不包含主机端口）
     * value：服务列表（负载均衡）
     */
    private static Map<String, List<ServiceMetaInfo>> serviceCacheMap = new ConcurrentHashMap<>();

    /**
     * @description 查询全部缓存
     * @author fangchenxin
     * @date 2024/7/11 00:19
     * @return java.util.Map<java.lang.String, java.util.List < com.chenxin.rpc.model.ServiceMetaInfo>>
     */
    Map<String, List<ServiceMetaInfo>> readAll() {
        return serviceCacheMap;
    }

    /**
     * @description 写缓存
     * @author fangchenxin
     * @date 2024/4/5 12:29
     * @param newServiceCache
     */
    void writeCache(String serviceKey, List<ServiceMetaInfo> newServiceCache) {
        serviceCacheMap.put(serviceKey, newServiceCache);
    }

    /**
     * @description 读缓存
     * @author fangchenxin
     * @date 2024/4/5 12:30
     * @return java.util.List<com.chenxin.rpc.model.ServiceMetaInfo>
     */
    List<ServiceMetaInfo> readCache(String serviceKey) {
        return serviceCacheMap.get(serviceKey);
    }

    /**
     * @description 清空缓存
     * @author fangchenxin
     * @date 2024/4/5 12:30
     */
    void clearCache() {
        serviceCacheMap.clear();
    }

    /**
     * @description 移除缓存中的服务节点
     * @author fangchenxin
     * @date 2024/7/10 20:07
     * @param serviceNodeKey /rpc/***.UserService:1.0/localhost:8090
     */
    void removeCacheByServiceNodeKey(String serviceNodeKey) {
        serviceCacheMap.keySet().stream()
                .filter(serviceKey -> serviceNodeKey.startsWith(serviceKey))
                .forEach(serviceKey -> {
                    List<ServiceMetaInfo> serviceMetaInfoList = serviceCacheMap.get(serviceKey);
                    serviceMetaInfoList.removeIf(serviceMetaInfo -> serviceNodeKey.endsWith(serviceMetaInfo.getServiceNodeKey()));
                });
    }
}
