package com.chenxin.rpc.proxy;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author fangchenxin
 * @description Mock服务代理 （JDK动态代理）
 * @date
 * @modify
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {

    private static final Faker faker = new Faker();
    /**
     * @description 调用代理
     * @author fangchenxin
     * @date 2024/3/26 22:36
     * @param proxy
     * @param method
     * @param args
     * @return java.lang.Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据方法的返回值类型，生成特定的默认对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * @description 生成指定类型的默认值对象
     * @author fangchenxin
     * @date 2024/3/26 22:40
     * @param type
     * @return java.lang.Object
     */
    public Object getDefaultObject(Class<?> type) {
        // 基本类型
        if (type.isPrimitive()) {
            if (type == boolean.class) {
                return false;
            } else if (type == short.class) {
                return (short) 0;
            } else if (type == int.class) {
                return 0;
            } else if (type == long.class) {
                return 0L;
            } else if (type == char.class) {
                return '\u0000';
            } else if (type == byte.class) {
                return (byte) 0;
            } else if (type == float.class) {
                return 0.0f;
            } else if (type == double.class) {
                return 0.0d;
            }
        } else {
            if (type == String.class) {
                // 伪造的单词
                return faker.lorem().word();
            }
            if (type == Date.class) {
                // 伪造的日期
                return faker.date().birthday();
            }
        }
        return null;
    }
}
