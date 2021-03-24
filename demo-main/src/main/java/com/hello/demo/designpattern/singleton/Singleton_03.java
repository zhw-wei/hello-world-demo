package com.hello.demo.designpattern.singleton;

import java.util.Objects;

public class Singleton_03 {

    private static Singleton_03 singleton;

    private Singleton_03(){}

    public static Singleton_03 getInstance(){
        if(Objects.isNull(singleton))
            singleton = new Singleton_03();
        return singleton;
    }
}
