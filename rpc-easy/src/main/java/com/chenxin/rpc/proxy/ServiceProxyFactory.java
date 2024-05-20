package com.chenxin.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class ServiceProxyFactory {

    /**
     * @description: 根据服务类获取代理对象
     * @param
     * @return:
     * @author: fangchenxin
     * @date: 2024/3/24 19:48
     * @modify:
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }

}
