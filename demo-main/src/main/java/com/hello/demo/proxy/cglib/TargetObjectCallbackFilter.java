package com.hello.demo.proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 定义回调过滤器
 */
public class TargetObjectCallbackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        System.out.println(method.getName());
        return 0;
    }
}
