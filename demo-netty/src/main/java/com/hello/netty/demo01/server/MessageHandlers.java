package com.hello.netty.demo01.server;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息处理器注册中心
 */
public class MessageHandlers {
    private static Map<String, IMessageHandler<?>> handlerMap = new HashMap<>();
    private static DefaultHandler defaultHandler = new DefaultHandler();

    public static void register(String type, IMessageHandler<?> handler){
        handlerMap.put(type, handler);
    }

    public static IMessageHandler<?> get(String type){
        return handlerMap.getOrDefault(type, defaultHandler);
    }
}
