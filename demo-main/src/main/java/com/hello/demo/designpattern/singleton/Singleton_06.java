package com.hello.demo.designpattern.singleton;

import java.util.Objects;

public class Singleton_06 {

    private static volatile Singleton_06 singleton;

    private Singleton_06(){}

    public static Singleton_06 getInstance(){
        if(Objects.isNull(singleton)){
            synchronized (Singleton_06.class){
                if(Objects.isNull(singleton))
                    singleton = new Singleton_06();
            }
        }
        return singleton;
    }
}
