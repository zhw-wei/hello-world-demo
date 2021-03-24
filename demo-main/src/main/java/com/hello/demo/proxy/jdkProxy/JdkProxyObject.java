package com.hello.demo.proxy.jdkProxy;

public class JdkProxyObject implements JdkProxyInterface{

    @Override
    public String helloWorld() {
        System.out.println("hello world");
        return "hello world";
    }

    @Override
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

    @Override
    public String world() {
        System.out.println("world");
        return "world";
    }
}
