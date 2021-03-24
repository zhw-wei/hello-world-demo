package com.hello.demo.reflection.handler;

public interface ItemHandler {

    String operator();

    boolean enable();

    boolean handle(String leftValue, String rightValue);
}
