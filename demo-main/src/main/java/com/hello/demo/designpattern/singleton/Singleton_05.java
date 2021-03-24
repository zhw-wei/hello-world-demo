package com.hello.demo.designpattern.singleton;

import java.util.Objects;

public class Singleton_05 {

    private static Singleton_05 singleton;

    private Singleton_05(){}

    public static Singleton_05 getInstance(){
        if(Objects.isNull(singleton)){
            synchronized (Singleton_05.class){
                singleton = new Singleton_05();
            }
        }
        return singleton;
    }
}
