package com.hello.demo.designpattern.facade;

/**
 * 影院灯光
 */
public class TheaterLight {

    private static TheaterLight theaterLight = new TheaterLight();

    public static TheaterLight getInstance(){
        return theaterLight;
    }

    public void on(){
        System.out.println("theater light on");
    }

    public void off(){
        System.out.println("theater light off");
    }

    public void dim(){
        System.out.println("theater light dim");
    }

    public void bright(){
        System.out.println("theater light bright ...");
    }
}
