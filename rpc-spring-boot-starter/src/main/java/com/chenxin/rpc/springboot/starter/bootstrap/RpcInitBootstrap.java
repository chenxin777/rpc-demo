package com.chenxin.rpc.springboot.starter.bootstrap;

import com.chenxin.rpc.RpcApplication;
import com.chenxin.rpc.config.RpcConfig;
import com.chenxin.rpc.server.tcp.VertTcpServer;
import com.chenxin.rpc.springboot.starter.annotation.EnableRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Objects;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
@Slf4j
public class RpcInitBootstrap implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获取EnableRpc注解的属性值
        boolean needServer = (boolean) Objects.requireNonNull(importingClassMetadata.getAnnotationAttributes(EnableRpc.class.getName())).get("needServer");
        // RPC框架初始化
        RpcApplication.init();
        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        // 启动服务器
        if (needServer) {
            VertTcpServer vertTcpServer = new VertTcpServer();
            vertTcpServer.doStart(rpcConfig.getServerPort());
        } else {
            log.info("不启动server");
        }
    }
}
