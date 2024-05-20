package com.chenxin.rpc.springboot.starter.annotation;

import com.chenxin.rpc.constant.RpcConstant;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务提供者注解(用于注册服务)
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    /**
     * @description 服务接口类
     * @author fangchenxin
     * @date 2024/4/9 22:26
     * @return java.lang.Class<?>
     */
    Class<?> interfaceClass() default void.class;

    /**
     * @description 版本
     * @author fangchenxin
     * @date 2024/4/9 22:26
     * @return java.lang.String
     */
    String serviceVersion() default RpcConstant.DEFAULT_SERVICE_VERSION;

}
