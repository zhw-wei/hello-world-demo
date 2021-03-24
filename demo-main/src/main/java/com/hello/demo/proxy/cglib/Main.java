package com.hello.demo.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class Main {
    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
//        enhancer.setCallback(new TargetInterceptor());
        enhancer.setCallbacks(new Callback[]{new TargetInterceptor(), new TargetResultFixed()});
        enhancer.setCallbackFilter(new TargetObjectCallbackFilter());

        TargetObject object = (TargetObject) enhancer.create();

        System.out.println("====================");
        System.out.println(object.method1(false));

        System.out.println("====================");
        System.out.println(object.method2("abc"));

        System.out.println("====================");
        object.method3();

        System.out.println("========== hello ==========");
        TargetObject targetObject = (TargetObject) new HelloInterceptor(TargetObject.class).getObject();
        targetObject.method1(false);

    }
}
