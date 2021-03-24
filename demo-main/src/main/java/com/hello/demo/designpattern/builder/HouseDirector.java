package com.hello.demo.designpattern.builder;

import lombok.Setter;

//指挥者，指定制作流程
public class HouseDirector {
    @Setter private HouseBuilder houseBuilder;

    public HouseDirector(HouseBuilder houseBuilder){
        this.houseBuilder = houseBuilder;
    }

    //建造房子的流程，交给指挥者
    public House buildHouse(){
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}
