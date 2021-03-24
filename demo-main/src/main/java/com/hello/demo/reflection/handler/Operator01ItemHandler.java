package com.hello.demo.reflection.handler;

public class Operator01ItemHandler implements ItemHandler {
    @Override
    public String operator() {
        return "01";
    }

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public boolean handle(String leftValue, String rightValue) {
        System.out.println("handle 01");

        return true;
    }
}
