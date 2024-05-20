package com.chenxin.rpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * @author fangchenxin
 * @description 配置工具类
 * @date
 * @modify
 */
public class ConfigUtils {

    /**
     * @description 加载配置对象
     * @author fangchenxin
     * @date 2024/3/25 20:23
     * @param tClass
     * @param prefix
     * @return T
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * @description 加载配置文件
     * @author fangchenxin
     * @date 2024/3/25 10:53
     * @param tClass
     * @param prefix 前缀
     * @param environment 环境
     * @return T
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        // 环境配置
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
            configFileBuilder.append(".properties");
            Props props = new Props(configFileBuilder.toString());
            return props.toBean(tClass, prefix);
    }


}
