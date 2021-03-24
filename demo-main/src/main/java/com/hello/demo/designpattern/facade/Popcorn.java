package com.hello.demo.designpattern.facade;

/**
 * 爆米花
 */
public class Popcorn {

    private static Popcorn popcorn = new Popcorn();

    public static Popcorn getInstance(){
        return popcorn;
    }

    public void on(){
        System.out.println("popcorn on");
    }

    public void off(){
        System.out.println("popcorn off");
    }

    public void pop(){
        System.out.println("popcorn pop");
    }
}
