package com.hello.demo.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyObjectV<T> implements InvocationHandler {

    private T object;

    public ProxyObjectV(T object) {
        this.object = object;
    }

    public T getObject() {
        return (T) Proxy.newProxyInstance(this.object.getClass().getClassLoader(),
                this.object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法增强
        System.out.println("======== start ========");

        //执行目标方法
        Object obj = method.invoke(this.object, args);

        System.out.println("======== end ========");
        return obj;
    }
}
