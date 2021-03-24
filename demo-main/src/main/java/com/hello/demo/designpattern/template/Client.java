package com.hello.demo.designpattern.template;

public class Client {
    public static void main(String[] args) {
        SoyaMilk readSoyaMilk = new ReadBeanSoyaMilk();
        readSoyaMilk.make();

        System.out.println("=====");
        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();

        System.out.println("=====");
        SoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }
}
