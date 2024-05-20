package com.chenxin.rpc.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.yaml.YamlUtil;
import com.chenxin.rpc.config.RpcConfig;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class ConfigYmlUtils {

    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        // 环境配置
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".yml");
        Object config = YamlUtil.loadByPath(configFileBuilder.toString()).get("rpc");
        return (T)BeanUtil.toBean(config, RpcConfig.class);
    }
}
