package com.hello.demo.designpattern.adapter.objectadapter;

//适配器类
public class VoltageAdapter implements IVoltage5V {
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V){
        this.voltage220V = voltage220V;
    }
    @Override
    public Integer output5V() {
        Integer src = voltage220V.output220V();
        Integer dst = src/44;
        System.out.println("adapter out put " + dst);
        return dst;
    }
}
