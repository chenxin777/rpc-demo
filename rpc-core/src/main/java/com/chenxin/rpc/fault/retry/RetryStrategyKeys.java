package com.chenxin.rpc.fault.retry;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public interface RetryStrategyKeys {

    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";
}
