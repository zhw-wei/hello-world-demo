package com.hello.demo.designpattern.facade;

/**
 * 立体声
 */
public class Stereo {

    private static Stereo stereo = new Stereo();

    public static Stereo getInstance(){
        return stereo;
    }

    public void on(){
        System.out.println("stereo on");
    }

    public void off(){
        System.out.println("stereo off");
    }

    public void up(){
        System.out.println("stereo up ...");
    }
}
