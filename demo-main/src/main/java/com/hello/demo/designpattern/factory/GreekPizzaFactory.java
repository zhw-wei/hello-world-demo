package com.hello.demo.designpattern.factory;

public class GreekPizzaFactory {

    public static Pizza createPizza(Integer type){
        Pizza pizza = null;
        if(type==0){
            pizza = new GreekPizza_0();
        }else if(type==1){
            pizza = new GreekPizza_1();
        }
        return pizza;
    }
}
