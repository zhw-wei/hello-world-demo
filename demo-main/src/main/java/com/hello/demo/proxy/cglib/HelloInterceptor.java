package com.hello.demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class HelloInterceptor implements MethodInterceptor {

    private Class classInfo;

    public HelloInterceptor(Class classInfo){
        this.classInfo = classInfo;
    }

    public Object getObject(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classInfo);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("invoke before ....");

        Object object = methodProxy.invokeSuper(o, objects);

        System.out.println("invoke after ....");
        return object;
    }
}
