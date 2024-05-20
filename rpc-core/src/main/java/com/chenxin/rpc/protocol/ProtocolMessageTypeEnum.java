package com.chenxin.rpc.protocol;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public enum ProtocolMessageTypeEnum {
    REQUEST(0),
    RESPONSE(1),
    HEART_BEAT(2),
    OTHERS(3);

    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }

    /**
     * @description 根据key获取枚举
     * @author fangchenxin
     * @date 2024/4/6 23:30
     * @param key
     * @return com.chenxin.rpc.protocol.ProtocolMessageTypeEnum
     */
    public static ProtocolMessageTypeEnum getEnumByKey(int key) {
        for (ProtocolMessageTypeEnum protocolMessageTypeEnum : ProtocolMessageTypeEnum.values()) {
            if (protocolMessageTypeEnum.key == key) {
                return protocolMessageTypeEnum;
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }
}
