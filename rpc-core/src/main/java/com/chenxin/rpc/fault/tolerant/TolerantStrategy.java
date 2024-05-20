package com.chenxin.rpc.fault.tolerant;

import com.chenxin.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author fangchenxin
 * @description 容错策略
 * @date
 * @modify
 */
public interface TolerantStrategy {

    /**
     * @description 容错
     * @author fangchenxin
     * @date 2024/4/9 13:07
     * @param context 上下文传递数据
     * @param e 异常
     * @return com.chenxin.rpc.model.RpcResponse
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}
