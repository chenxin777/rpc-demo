package com.chenxin.rpc.springboot.starter.bootstrap;

import com.chenxin.rpc.RpcApplication;
import com.chenxin.rpc.config.RegistryConfig;
import com.chenxin.rpc.config.RpcConfig;
import com.chenxin.rpc.model.ServiceMetaInfo;
import com.chenxin.rpc.registry.LocalRegistry;
import com.chenxin.rpc.registry.Registry;
import com.chenxin.rpc.registry.RegistryFactory;
import com.chenxin.rpc.springboot.starter.annotation.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author fangchenxin
 * @description Rpc服务提供者启动
 * @date
 * @modify
 */
public class RpcProviderBootstrap implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        RpcService rpcService = beanClass.getAnnotation(RpcService.class);
        if (rpcService != null) {
            // 需要注册服务
            // 1、获取服务基本信息
            Class<?> interfaceClass = rpcService.interfaceClass();
            // 默认值处理
            if (interfaceClass == void.class) {
                interfaceClass = beanClass.getInterfaces()[0];
            }
            // 服务名称
            String serviceName = interfaceClass.getName();
            // 版本
            String serviceVersion = rpcService.serviceVersion();
            // 2、注册服务
            // 本地注册
            LocalRegistry.register(serviceName, beanClass);
            // 全局配置
            final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            // 注册中心
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            // 服务信息类
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(serviceVersion);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception ex) {
                throw new RuntimeException(serviceName + "服务注册失败", ex);
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
