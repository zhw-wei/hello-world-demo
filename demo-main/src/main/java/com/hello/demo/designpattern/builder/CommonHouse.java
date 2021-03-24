package com.hello.demo.designpattern.builder;

public class CommonHouse extends HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("common build basic");
        this.house.setBaise("common");
    }

    @Override
    public void buildWalls() {
        System.out.println("common build walls");
        this.house.setWall("common");
    }

    @Override
    public void roofed() {
        System.out.println("common roofed");
        this.house.setRoofed("common");
    }
}
