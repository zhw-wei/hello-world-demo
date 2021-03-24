package com.hello.demo.designpattern.abstractfactory;

//抽象工厂模式的抽象层
//传参数使用接口
public interface IPizzaFactory {
    Pizza createPizza(Integer type);
}
