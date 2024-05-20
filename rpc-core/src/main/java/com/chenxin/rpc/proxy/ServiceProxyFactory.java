package com.chenxin.rpc.proxy;

import com.chenxin.rpc.RpcApplication;

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
        if (RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }

    /**
     * @description 根据服务类获取Mock代理对象
     * @author fangchenxin
     * @date 2024/3/29 09:33
     * @param serviceClass
     * @return T
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy());
    }



}
