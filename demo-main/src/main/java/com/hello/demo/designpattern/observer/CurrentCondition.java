package com.hello.demo.designpattern.observer;

public class CurrentCondition implements Observer{

    private Float temp;
    private Float pre;
    private Float hum;

    @Override
    public void update(Float temp, Float pre, Float hum){
        this.temp = temp;
        this.pre = pre;
        this.hum = hum;
        this.display();
    }

    private void display(){
        System.out.println("temp: " + temp + ", pre: " + pre + ", hum: " + hum);
    }
}
