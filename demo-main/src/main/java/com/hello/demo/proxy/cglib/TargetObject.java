package com.hello.demo.proxy.cglib;

public class TargetObject {

    public boolean method1(boolean b){
        System.out.println("method1 ...");
        return b;
    }

    public String method2(String str){
        System.out.println("method2 ...");
        return str;
    }

    public void method3(){

        System.out.println("method3 ...");
    }
}
