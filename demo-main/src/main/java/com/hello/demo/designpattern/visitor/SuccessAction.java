package com.hello.demo.designpattern.visitor;

public class SuccessAction extends Action{
    @Override
    public void getManResult(Man person) {
        System.out.println("man success");
    }

    @Override
    public void getWomanResult(Woman person) {
        System.out.println("woman success");
    }
}
