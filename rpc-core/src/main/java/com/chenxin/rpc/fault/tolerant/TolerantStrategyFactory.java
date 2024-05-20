package com.chenxin.rpc.fault.tolerant;

import com.chenxin.rpc.spi.SpiLoader;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailFastTolerantStrategy();

    /**
     * @description 获取实例
     * @author fangchenxin
     * @date 2024/4/9 14:44
     * @param key
     * @return com.chenxin.rpc.fault.tolerant.TolerantStrategy
     */
    public static TolerantStrategy getInstant(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
