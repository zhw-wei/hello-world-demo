package com.hello.demo.designpattern.factory;

public class PizzaFactory {

    public static Pizza createPizza(Integer type, Integer p){
        Pizza pizza = null;
        if(p==0){
            pizza = ChessPizzaFactory.createPizza(type);
        }else if(p==1){
            pizza = GreekPizzaFactory.createPizza(type);
        }
        return pizza;
    }
}
