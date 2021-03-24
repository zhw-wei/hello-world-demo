package com.hello.demo.designpattern.builder;

//抽象的建造者
public abstract class HouseBuilder {
    protected House house = new House();

    //建造的流程
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();

    public House buildHouse(){
        return house;
    }
}
