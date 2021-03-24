package com.hello.demo.reflection.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Operator03ItemHandler implements ItemHandler{
    @Override
    public String operator() {
        return "03";
    }

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public boolean handle(String leftValue, String rightValue) {
        System.out.println("handle 03");
        return true;
    }
}
