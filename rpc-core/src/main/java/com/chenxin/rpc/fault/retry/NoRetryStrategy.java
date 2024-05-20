package com.chenxin.rpc.fault.retry;

import com.chenxin.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author fangchenxin
 * @description 不重试策略
 * @date
 * @modify
 */
public class NoRetryStrategy implements RetryStrategy{
    /**
     * @description 重试
     * @author fangchenxin
     * @date 2024/4/8 19:36
     * @param callable
     * @return com.chenxin.rpc.model.RpcResponse
     */
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
