package com.hello.demo.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 简单的对象转换工具，不通用
 */
public class SimpleBeanCopyUtil<T, X> {

    public SimpleBeanCopyUtil() {
    }

    public X doCopy(T tObj, X xObj) {
        this.initMethodMap(readMethodMap, tObj.getClass(), true);
        this.initMethodMap(writeMethodMap, xObj.getClass(), false);

        writeMethodMap.forEach((key, writeMethod) -> {
            Method readMethod = readMethodMap.get(key);
            if (Objects.nonNull(readMethod)) {
                try {
                    Object value = readMethod.invoke(tObj);
                    writeMethod.invoke(xObj, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return xObj;
    }

    private Map<String, Method> readMethodMap = new HashMap<>();
    private Map<String, Method> writeMethodMap = new HashMap<>();

    private void initMethodMap(Map<String, Method> methodMap, Class obj, Boolean read) {
        if (!methodMap.isEmpty()) return;
        for (Field field : obj.getDeclaredFields()) {
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), obj);
                Method method = read ? descriptor.getReadMethod() : descriptor.getWriteMethod();
                methodMap.put(field.getName(), method);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
    }
}
