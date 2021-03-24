package com.hello.demo.designpattern.singleton;

public class Singleton_01 {

    private static Singleton_01 singleton = new Singleton_01();

    private Singleton_01(){}

    public static Singleton_01 getInstance(){
        return singleton;
    }
}
