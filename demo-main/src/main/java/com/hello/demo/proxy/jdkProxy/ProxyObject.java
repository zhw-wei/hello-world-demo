package com.hello.demo.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyObject implements InvocationHandler {

    //object 需要是一个接口的实例对象
    private Object object;

    public ProxyObject(Object object){
        this.object = object;
    }

    /**
     * 获取代理对象
     * @return
     */
    public Object getObject(){
        Object object = Proxy.newProxyInstance(this.object.getClass().getClassLoader(), this.object.getClass().getInterfaces(), this);
        return object;
    }

    /**
     * invoke方法在调用方法时会被自动调用
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法增强
        System.out.println("======== start ========");

        //执行目标方法
        Object obj = method.invoke(object, args);

        System.out.println("======== end ========");
        return obj;
    }
}
