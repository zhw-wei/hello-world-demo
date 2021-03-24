package com.hello.demo.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * interceptor 拦截器
 * 定义方法拦截器
 */
public class TargetInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before intercept ...");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("after intercept ...");
        return result;
    }
}
