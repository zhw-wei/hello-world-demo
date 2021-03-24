package com.hello.demo.designpattern.singleton;


public class Singleton_07 {

    private Singleton_07(){}

    public static Singleton_07 getInstance(){
        return SingletonInstance.INSTANCE;
    }

    private static class SingletonInstance{
        private static final Singleton_07 INSTANCE = new Singleton_07();
    }
}
