package com.chenxin.rpc.fault.tolerant;

import com.chenxin.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author fangchenxin
 * @description 转移到其他服务节点
 * @date
 * @modify
 */
public class FailOverTolerantStrategy implements TolerantStrategy{

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        return null;
    }
}
