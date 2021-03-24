package com.hello.demo.designpattern.observer;

public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentCondition currentCondition = new CurrentCondition();
        CurrentCondition currentCondition_1 = new CurrentCondition();

        weatherData.registerObserver(currentCondition);
        weatherData.registerObserver(currentCondition_1);

        weatherData.setData(0.1f, 0.2f, 0.3f);
    }
}
