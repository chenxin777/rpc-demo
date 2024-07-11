package com.chenxin.rpc.model;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
@Data
public class ServiceMetaInfo {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本号
     */
    private String serviceVersion = "1.0";

    /**
     * 服务主机
     */
    private String serviceHost;

    /**
     * 服务端口
     */
    private int servicePort;

    /**
     * 服务分组（暂未实现）
     */
    private String serviceGroup = "default";

    private String registerTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");

    /**
     * @description 获取服务键名(服务名:版本)
     * @author fangchenxin
     * @date 2024/4/3 17:59
     * @return java.lang.String
     */
    public String getServiceKey() {
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * @description 获取服务注册节点键名 服务名:版本/主机:端口
     * @author fangchenxin
     * @date 2024/4/3 18:01
     * @return java.lang.String
     */
    public String getServiceNodeKey() {
        //return String.format("%s/%s", getServiceKey(), serviceAddress);
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }

    /**
     * @description 获取完整服务地址(http://主机:端口)
     * @author fangchenxin
     * @date 2024/4/4 18:15
     * @return java.lang.String
     */
    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }

}
