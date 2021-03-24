package com.hello.demo.designpattern.decorator;

public class MilkDecorator extends CoffeeDecorator{

    public MilkDecorator(Drink drink) {
        super(drink);
        this.setDes("milk");
        this.setPrice(2.0f);
    }
}
