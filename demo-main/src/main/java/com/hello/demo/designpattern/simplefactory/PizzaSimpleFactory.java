package com.hello.demo.designpattern.simplefactory;

public class PizzaSimpleFactory {

    public static Pizza createPizza(Integer type){
        System.out.println("pizza build start");

        Pizza pizza = null;
        if(type == 1){
            pizza = new GreekPizza();
        }else if(type == 2){
            pizza = new ChessPizza();
        }

        return pizza;
    }
}
