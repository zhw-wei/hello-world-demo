package com.hello.demo.designpattern.singleton;

public class Singleton_02 {

    private static Singleton_02 singleton;

    static {
        singleton = new Singleton_02();
    }

    private Singleton_02(){}

    public static Singleton_02 getInstance(){
        return singleton;
    }
}
