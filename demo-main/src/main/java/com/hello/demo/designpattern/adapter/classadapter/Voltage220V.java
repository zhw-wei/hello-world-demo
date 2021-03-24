package com.hello.demo.designpattern.adapter.classadapter;

//被适配的类
public class Voltage220V {

    public Integer output220V(){
        Integer src = 220;
        System.out.println("out put 220V");
        return src;
    }
}
