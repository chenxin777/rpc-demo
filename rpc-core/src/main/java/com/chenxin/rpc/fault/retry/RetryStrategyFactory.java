package com.chenxin.rpc.fault.retry;

import com.chenxin.rpc.spi.SpiLoader;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class RetryStrategyFactory {

    static {
        SpiLoader.load(RetryStrategy.class);
    }

    /**
     * 默认重试器（不重试）
     */
    private static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();

    /**
     * @description 获取实例
     * @author fangchenxin
     * @date 2024/4/8 22:08
     * @param key
     * @return com.chenxin.rpc.fault.retry.RetryStrategy
     */
    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }
}
