package com.chenxin.rpc.springboot.starter.annotation;

import com.chenxin.rpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.chenxin.rpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.chenxin.rpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用Rpc注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * @description 需要启动server
     * @author fangchenxin
     * @date 2024/4/10 12:29
     * @return boolean
     */
    boolean needServer() default true;

}
