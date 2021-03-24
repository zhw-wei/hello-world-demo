package com.hello.demo.designpattern.singleton;

import java.util.Objects;

public class Singleton_04 {

    private static Singleton_04 singleton;

    private Singleton_04(){}

    public static synchronized Singleton_04 getInstance(){
        if(Objects.isNull(singleton))
            singleton = new Singleton_04();
        return singleton;
    }
}
