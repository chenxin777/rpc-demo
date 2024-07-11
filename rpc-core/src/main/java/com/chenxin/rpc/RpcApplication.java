package com.chenxin.rpc;

import com.chenxin.rpc.config.RegistryConfig;
import com.chenxin.rpc.config.RpcConfig;
import com.chenxin.rpc.constant.RpcConstant;
import com.chenxin.rpc.registry.Registry;
import com.chenxin.rpc.registry.RegistryFactory;
import com.chenxin.rpc.utils.ConfigUtils;
import com.chenxin.rpc.utils.ConfigYmlUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fangchenxin
 * @description Rpc框架应用
 * 相当于holder，存放了项目全局用到的变量。双检锁单例模式实现
 * @date
 * @modify
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * @param newRpcConfig
     * @description 框架初始化，支持传入自定义配置
     * @author fangchenxin
     * @date 2024/3/25 21:25
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        // 创建并注册Shutdown Hook，JVM退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    /**
     * @description 加载全局配置
     * @author fangchenxin
     * @date 2024/4/11 17:34
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            //newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
            newRpcConfig = ConfigYmlUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * @return com.chenxin.rpc.config.RpcConfig
     * @description 获取配置
     * @author fangchenxin
     * @date 2024/3/25 21:31
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
