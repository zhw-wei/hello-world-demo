package com.hello.demo.reflection;

import com.hello.demo.reflection.handler.ItemHandler;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Main {
    /**
     * <h2>Reflections通过扫描classpath，索引元数据，并且允许在运行时查询这些元数据。</h2>
     * <h3>使用Reflections可以很轻松的获取以下元数据信息：</h3>
     * <ol>
     *  <li>获取某个类型的所有子类；比如，有一个父类是TestInterface，可以获取到TestInterface的所有子类。</li>
     *  <li>获取某个注解的所有类型/字段变量，支持注解参数匹配。</li>
     *  <li>使用正则表达式获取所有匹配的资源文件</li>
     *  <li>获取特定签名方法。</li>
     * </ol>
     */
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        ConfigurationBuilder builder = new ConfigurationBuilder()
                .forPackages(ItemHandler.class.getPackageName())
                .addScanners(new SubTypesScanner());    //具体有多少个Scanner可以查看源码
        Reflections reflections = new Reflections(builder);

        var handlerMap = new HashMap<String, ItemHandler>();
        for (Class<? extends ItemHandler> handlerClass : reflections.getSubTypesOf(ItemHandler.class)) {
            ItemHandler itemHandler = handlerClass.getDeclaredConstructor().newInstance();
            if (itemHandler.enable())
                handlerMap.put(itemHandler.operator(), itemHandler);
        }

        System.out.println(handlerMap);
    }
}
