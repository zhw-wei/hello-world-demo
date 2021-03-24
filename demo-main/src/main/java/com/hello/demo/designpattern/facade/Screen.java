package com.hello.demo.designpattern.facade;

/**
 * 屏幕
 */
public class Screen {

    private static Screen screen = new Screen();

    public static Screen getInstance(){
        return screen;
    }

    public void up(){
        System.out.println("screen on");
    }

    public void down(){
        System.out.println("screen down");
    }
}
