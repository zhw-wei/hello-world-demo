package com.hello.demo.designpattern.builder;

public class HighBuilding extends HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("high build basic");
        this.house.setBaise("high");
    }

    @Override
    public void buildWalls() {
        System.out.println("high build walls");
        this.house.setWall("high");
    }

    @Override
    public void roofed() {
        System.out.println("high roofed");
        this.house.setRoofed("high");
    }
}
