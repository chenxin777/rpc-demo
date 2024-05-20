package com.chenxin.rpc.fault.tolerant;

import com.chenxin.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author fangchenxin
 * @description 降级到其他服务容错策略
 * @date
 * @modify
 */
public class FailBackTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        return null;
    }
}
