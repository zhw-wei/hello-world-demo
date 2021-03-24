package com.hello.demo.designpattern.abstractfactory;

public class ChessPizzaFactory implements IPizzaFactory{

    public Pizza createPizza(Integer type){
        Pizza pizza = null;
        if(type == 0){
            pizza = new ChessPizza_0();
        }else if(type == 1){
            pizza = new ChessPizza_1();
        }
        return pizza;
    }
}
