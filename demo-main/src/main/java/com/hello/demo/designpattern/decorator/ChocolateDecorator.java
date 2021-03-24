package com.hello.demo.designpattern.decorator;

public class ChocolateDecorator extends CoffeeDecorator{
    public ChocolateDecorator(Drink drink) {
        super(drink);
        this.setDes("chocolate");
        this.setPrice(3.0f);
    }
}
