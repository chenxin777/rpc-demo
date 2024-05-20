package com.chenxin.rpc.bootstrap;

import com.chenxin.rpc.RpcApplication;
import com.chenxin.rpc.config.RegistryConfig;
import com.chenxin.rpc.config.RpcConfig;
import com.chenxin.rpc.model.ServiceMetaInfo;
import com.chenxin.rpc.model.ServiceRegisterInfo;
import com.chenxin.rpc.registry.LocalRegistry;
import com.chenxin.rpc.registry.Registry;
import com.chenxin.rpc.registry.RegistryFactory;
import com.chenxin.rpc.server.tcp.VertTcpServer;

import java.util.List;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class ProviderBoostrap {

    public static void init(List<ServiceRegisterInfo> serviceRegisterInfoList) {
        // RPC框架初始化
        RpcApplication.init();
        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        // 注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());
            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception ex) {
                throw new RuntimeException(serviceName + "服务注册失败", ex);
            }
        }
        // 启动TCP web服务
        VertTcpServer tcpServer = new VertTcpServer();
        tcpServer.doStart(rpcConfig.getServerPort());
        // 启动http web服务
//        VertHttpServer httpServer = new VertHttpServer();
//        httpServer.doStart(rpcConfig.getServerPort());
    }
}
