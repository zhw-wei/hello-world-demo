package com.hello.demo.designpattern.builder;

public class Client {
    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        HouseDirector houseDirector = new HouseDirector(commonHouse);

        House house = houseDirector.buildHouse();
        System.out.println(house);
    }
}
