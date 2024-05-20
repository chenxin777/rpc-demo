package com.chenxin.rpc.fault.retry;

import com.chenxin.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author fangchenxin
 * @description 重试策略接口
 * @date
 * @modify
 */
public interface RetryStrategy {

    /**
     * @description 重试
     * @author fangchenxin
     * @date 2024/4/8 19:11
     * @param callable
     * @return com.chenxin.rpc.model.RpcResponse
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
