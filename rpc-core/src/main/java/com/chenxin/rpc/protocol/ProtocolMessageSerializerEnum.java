package com.chenxin.rpc.protocol;

import cn.hutool.core.util.ObjectUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fangchenxin
 * @description 协议消息的序列化器枚举
 * @date
 * @modify
 */
public enum ProtocolMessageSerializerEnum {
    JDK(0, "jdk"),
    JSON(1, "json"),
    KRYO(2, "kryo"),
    HESSIAN(3, "hessian");

    private final int key;

    private final String value;

    ProtocolMessageSerializerEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @description 获取值列表
     * @author fangchenxin
     * @date 2024/4/6 12:55
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * @description 根据key获取枚举
     * @author fangchenxin
     * @date 2024/4/6 13:35
     * @param key
     * @return com.chenxin.rpc.protocol.ProtocolMessageSerializerEnum
     */
    public static ProtocolMessageSerializerEnum getEnumByKey(int key) {
        for (ProtocolMessageSerializerEnum protocolMessageSerializerEnum : ProtocolMessageSerializerEnum.values()) {
            if (protocolMessageSerializerEnum.key == key) {
                return protocolMessageSerializerEnum;
            }
        }
        return null;
    }

    /**
     * @description 根据value获取枚举
     * @author fangchenxin
     * @date 2024/4/6 13:41
     * @param value
     * @return com.chenxin.rpc.protocol.ProtocolMessageSerializerEnum
     */
    public static ProtocolMessageSerializerEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (ProtocolMessageSerializerEnum protocolMessageSerializerEnum : ProtocolMessageSerializerEnum.values()) {
            if (protocolMessageSerializerEnum.value.equals(value)) {
                return protocolMessageSerializerEnum;
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
