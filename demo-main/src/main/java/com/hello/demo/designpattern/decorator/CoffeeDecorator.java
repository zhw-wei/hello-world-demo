package com.hello.demo.designpattern.decorator;

public class CoffeeDecorator extends Drink{

    private Drink drink;

    public CoffeeDecorator(Drink drink){
        this.drink = drink;
    }

    @Override
    public Float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDes(){
        return super.getDes() + " " + super.getPrice() + " && " + drink.getDes();
    }
}
