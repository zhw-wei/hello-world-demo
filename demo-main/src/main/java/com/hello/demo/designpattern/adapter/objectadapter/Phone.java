package com.hello.demo.designpattern.adapter.objectadapter;

public class Phone {

    public void charging(IVoltage5V voltage5V){
        if(voltage5V.output5V() == 5)
            System.out.println("start charging");
        else
            System.out.println("do not start charging");
    }
}
