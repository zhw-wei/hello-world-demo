package com.hello.demo.designpattern.facade;

public class Client {

    public static void main(String[] args) {
        HomeTheaterFacade theater = new HomeTheaterFacade();

        theater.ready();
        theater.play();

        theater.pause();
        theater.end();
    }
}
