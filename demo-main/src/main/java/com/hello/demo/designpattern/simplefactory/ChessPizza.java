package com.hello.demo.designpattern.simplefactory;

public class ChessPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("chess pizza prepare");
    }
}
