package com.chenxin.rpc.protocol;

/**
 * @author fangchenxin
 * @description 协议消息的状态枚举
 * @date
 * @modify
 */
public enum ProtocolMessageStatusEnum {
    OK("ok", 20),
    BAD_REQUEST("badRequest", 40),
    BAD_RESPONSE("badResponse", 50);


    private final String text;

    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * @description 根据value获取枚举
     * @author fangchenxin
     * @date 2024/4/6 12:27
     * @param value
     * @return com.chenxin.rpc.protocol.ProtocolMessageStatusEnum
     */
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum protocolMessageStatusEnum :ProtocolMessageStatusEnum.values()){
            if (protocolMessageStatusEnum.value == value) {
                return protocolMessageStatusEnum;
            }
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }
}
