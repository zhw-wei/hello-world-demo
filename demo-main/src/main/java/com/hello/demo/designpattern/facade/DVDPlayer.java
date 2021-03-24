package com.hello.demo.designpattern.facade;

/**
 * dvd
 */
public class DVDPlayer {

    private static DVDPlayer dvdPlayer = new DVDPlayer();

    public static DVDPlayer getInstance(){
        return dvdPlayer;
    }

    public void on(){
        System.out.println("dvd on");
    }

    public void off(){
        System.out.println("dvd off");
    }

    public void play(){
        System.out.println("dvd play ...");
    }

    public void pause(){
        System.out.println("dvd pause");
    }
}
