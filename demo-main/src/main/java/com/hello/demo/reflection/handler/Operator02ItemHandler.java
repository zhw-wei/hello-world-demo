package com.hello.demo.reflection.handler;

public class Operator02ItemHandler implements ItemHandler{
    @Override
    public String operator() {
        return "02";
    }

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public boolean handle(String leftValue, String rightValue) {
        System.out.println("handle 02");
        return true;
    }
}
