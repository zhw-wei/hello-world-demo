package com.hello.demo.designpattern.flyweight;

public class ConcreteWebSite extends WebSite{

    private String type = "";//网站发布形式（类型）

    public ConcreteWebSite(String type){
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("concrete " + this.type + ", user_name " + user.getName());
    }
}
