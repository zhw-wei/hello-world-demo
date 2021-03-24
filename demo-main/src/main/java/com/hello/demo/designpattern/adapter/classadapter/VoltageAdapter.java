package com.hello.demo.designpattern.adapter.classadapter;

//适配器类
public class VoltageAdapter extends Voltage220V implements IVoltage5V{
    @Override
    public Integer output5V() {
        Integer src = super.output220V();
        Integer dst = src/44;
        System.out.println("adapter out put " + dst);
        return dst;
    }
}
