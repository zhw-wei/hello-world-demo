package com.hello.demo.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{

    private Float temp;
    private Float pre;
    private Float hum;
    private List<Observer> observerList;

    public WeatherData(){
        this.observerList = new ArrayList<>();
    }

    public void setData(Float temp, Float pre, Float hum){
        this.temp = temp;
        this.pre = pre;
        this.hum = hum;
        this.notifyObserver();
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObserver() {
        observerList.forEach(o -> o.update(this.temp, this.pre, this.hum));
    }
}
