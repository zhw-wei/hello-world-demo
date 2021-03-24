package com.hello.demo.designpattern.visitor;

public class FailAction extends Action{
    @Override
    public void getManResult(Man person) {
        System.out.println("man fail");
    }

    @Override
    public void getWomanResult(Woman person) {
        System.out.println("woman fail");
    }
}
