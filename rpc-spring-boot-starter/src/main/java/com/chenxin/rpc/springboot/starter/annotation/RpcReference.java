package com.chenxin.rpc.springboot.starter.annotation;

import com.chenxin.rpc.constant.RpcConstant;
import com.chenxin.rpc.fault.retry.RetryStrategyKeys;
import com.chenxin.rpc.fault.tolerant.TolerantStrategyKeys;
import com.chenxin.rpc.loadbalancer.LoadBalancerKeys;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务消费者注解（用于注入服务）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RpcReference {

    /**
     * @description 服务接口类
     * @author fangchenxin
     * @date 2024/4/9 22:29
     * @return java.lang.Class<?>
     */
    Class<?> interfaceClass() default void.class;

    /**
     * @description 版本
     * @author fangchenxin
     * @date 2024/4/9 22:41
     * @return java.lang.String
     */
    String serviceVersion() default RpcConstant.DEFAULT_SERVICE_VERSION;

    /**
     * @description 负载均衡器
     * @author fangchenxin
     * @date 2024/4/9 22:42
     * @return java.lang.String
     */
    String loadBalancer() default LoadBalancerKeys.ROUND_ROBIN;

    /**
     * @description 重试策略
     * @author fangchenxin
     * @date 2024/4/9 22:43
     * @return java.lang.String
     */
    String retryStrategy() default RetryStrategyKeys.NO;

    /**
     * @description 容错策略
     * @author fangchenxin
     * @date 2024/4/9 22:43
     * @return java.lang.String
     */
    String tolerantStrategy() default TolerantStrategyKeys.FAIL_FAST;

    /**
     * @description 模拟调用
     * @author fangchenxin
     * @date 2024/4/9 22:44
     * @return boolean
     */
    boolean mock() default false;
}
