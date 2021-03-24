package com.hello.demo.designpattern.factory;

public class ChessPizzaFactory {

    public static Pizza createPizza(Integer type){
        Pizza pizza = null;
        if(type == 0){
            pizza = new ChessPizza_0();
        }else if(type == 1){
            pizza = new ChessPizza_1();
        }
        return pizza;
    }
}
