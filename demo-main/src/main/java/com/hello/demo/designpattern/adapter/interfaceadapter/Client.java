package com.hello.demo.designpattern.adapter.interfaceadapter;

public class Client {
    public static void main(String[] args) {
        Adapter adapter = new AbstractAdapter() {
            @Override
            public void fun_1() {
                System.out.println("hello adapter fun 1");
            }
        };

        adapter.fun_0();
        adapter.fun_1();
    }
}
