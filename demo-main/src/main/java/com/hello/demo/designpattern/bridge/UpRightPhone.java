package com.hello.demo.designpattern.bridge;

//直立式手机类
public class UpRightPhone extends Phone{
    public UpRightPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        super.open();
        System.out.println("up right phone");
    }

    public void close(){
        super.close();
        System.out.println("up right phone");
    }

    public void call(){
        super.call();
        System.out.println("up right phone");
    }
}
