package com.hello.demo.designpattern.abstractfactory;

public abstract class Pizza {
    protected String name;

    //准备原材料
    public abstract void prepare();

    public void bake(){
        System.out.println(name + " baking;");
    }
    public void cut(){
        System.out.println(name + " cutting;");
    }
    public void box(){
        System.out.println(name + " boxing;");
    }

    public void setName(String name){
        this.name = name;
    }
}
