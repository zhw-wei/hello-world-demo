package com.hello.demo.designpattern.simplefactory;

public class GreekPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("greek pizza prepare");
    }
}
