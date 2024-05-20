package com.chenxin.rpc.serializer;

import com.chenxin.rpc.exception.NoSuchLoadClassException;
import com.chenxin.rpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangchenxin
 * @description 序列化器工厂
 * @date
 * @modify
 */
public class SerializerFactory {

    /**
     * 序列化映射（用于实现单例）
     */
    /*private static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<String, Serializer>() {
        {
            put(SerializerKeys.JDK, new JdkSerializer());
            put(SerializerKeys.JSON, new JsonSerializer());
            put(SerializerKeys.KRYO, new KryoSerializer());
            put(SerializerKeys.HESSIAN, new HessianSerializer());
        }
    };*/

    /**
     * 静态代码块，首次加载时，调用load方法加载序列化器接口的所有实现类，之后调用getInstance方法获取指定的实现类对象
     */
    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
//    private static final Serializer DEFAULT_SERIALIZER = KEY_SERIALIZER_MAP.get(SerializerKeys.JDK);
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * @description 获取实例
     * @author fangchenxin
     * @date 2024/4/1 23:40
     * @param key
     * @return com.chenxin.rpc.serializer.Serializer.Serializer
     */
    /*public static Serializer getInstance(String key) {
        return KEY_SERIALIZER_MAP.getOrDefault(key, DEFAULT_SERIALIZER);
    }*/
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }

    /**
     * @description 懒加载获取序列化对象
     * @author fangchenxin
     * @date 2024/4/12 15:32
     * @param key
     * @return com.chenxin.rpc.serializer.Serializer
     */
    public static Serializer getSerializer(String key) {
        Serializer serializer;
        try {
            serializer = SpiLoader.getInstance(Serializer.class, key);
        } catch (NoSuchLoadClassException ex) {
            init();
            serializer = SpiLoader.getInstance(Serializer.class, key);
        }
        return serializer;
    }

    /**
     * @description SPI加载
     * @author fangchenxin
     * @date 2024/4/12 15:33
     */
    public synchronized static void init() {
        SpiLoader.load(Serializer.class);
    }
}
