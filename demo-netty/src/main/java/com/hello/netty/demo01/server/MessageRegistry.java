package com.hello.netty.demo01.server;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息类型注册中心
 */
public class MessageRegistry {
    private static Map<Integer, Class<?>> clazzMap = new HashMap<>();

    public static void register(int type, Class<?> clazz){
        clazzMap.put(type, clazz);
    }

    public static Class<?> get(int type){
        return clazzMap.get(type);
    }
}
