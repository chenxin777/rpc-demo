package com.chenxin.rpc.fault.tolerant;

import com.chenxin.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author fangchenxin
 * @description 快速失败容错策略（立刻通知外层调用方）
 * @date
 * @modify
 */
public class FailFastTolerantStrategy implements TolerantStrategy{

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错", e);
    }
}
