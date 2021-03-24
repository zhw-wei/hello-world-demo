package com.hello.demo.designpattern.facade;

/**
 * 投影仪
 */
public class Projector {

    private static Projector projector = new Projector();

    public static Projector getInstance(){
        return projector;
    }

    public void on(){
        System.out.println("projector on");
    }

    public void off(){
        System.out.println("projector off");
    }

    public void focus(){
        System.out.println("projector focus");
    }
}
