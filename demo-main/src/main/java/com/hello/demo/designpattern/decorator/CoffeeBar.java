package com.hello.demo.designpattern.decorator;

public class CoffeeBar {
    public static void main(String[] args) {
        //两份巧克力+一份牛奶+BlackCoffee

        //点一份black
        Drink order = new BlackCoffee();
        System.out.println("cost1 = " + order.cost() + ", desc = " + order.getDes());

        //加入一份牛奶
        order = new MilkDecorator(order);
        System.out.println("order add milk = " + order.cost() + ", desc = " + order.getDes());

        //加入一份巧克力
        order = new ChocolateDecorator(order);
        System.out.println("order add chocolate = " + order.cost() + ", desc = " + order.getDes());

        //加入一份巧克力
        order = new ChocolateDecorator(order);
        System.out.println("order add chocolate again = " + order.cost() + ", desc = " + order.getDes());
    }
}
