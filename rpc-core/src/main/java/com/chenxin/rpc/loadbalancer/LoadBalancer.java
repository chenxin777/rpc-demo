package com.chenxin.rpc.loadbalancer;

import com.chenxin.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public interface LoadBalancer {

    /**
     * @description 选择服务调用
     * @author fangchenxin
     * @date 2024/4/8 15:20
     * @param requestParams 请求参数
     * @param serviceMetaInfoList 可用服务列表
     * @return com.chenxin.rpc.model.ServiceMetaInfo
     */
    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);

}
