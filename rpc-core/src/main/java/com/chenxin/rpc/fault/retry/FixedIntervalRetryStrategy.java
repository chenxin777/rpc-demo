package com.chenxin.rpc.fault.retry;

import com.chenxin.rpc.model.RpcResponse;
import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author fangchenxin
 * @description 固定时间间隔重试策略
 * @date
 * @modify
 */
@Slf4j
public class FixedIntervalRetryStrategy implements RetryStrategy{

    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                // 指定当出现Exception异常时重试
                .retryIfExceptionOfType(Exception.class)
                // 选择fixedWait固定时间间隔策略
                .withWaitStrategy(WaitStrategies.fixedWait(3L, TimeUnit.SECONDS))
                // 选择stopAfterAttempt超过最大重试次数停止
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                // 监听重试，除了再次执行任务外，还能打印重试次数
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("[FixedIntervalRetryStrategy] 请求次数 {}", attempt.getAttemptNumber());
                    }
                })
                .build();
        return retryer.call(callable);
    }
}
