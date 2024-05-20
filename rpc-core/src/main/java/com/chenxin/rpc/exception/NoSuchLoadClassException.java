package com.chenxin.rpc.exception;

/**
 * @author fangchenxin
 * @description 没有加载loadClass
 * @date
 * @modify
 */
public class NoSuchLoadClassException extends RuntimeException{

    public NoSuchLoadClassException() {
    }

    public NoSuchLoadClassException(String message) {
        super(message);
    }
}
