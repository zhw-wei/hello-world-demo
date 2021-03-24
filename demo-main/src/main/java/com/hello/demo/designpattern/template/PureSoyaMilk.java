package com.hello.demo.designpattern.template;

public class PureSoyaMilk extends SoyaMilk{
    @Override
    public void addCondiment() {
        //nothing
    }

    @Override
    public Boolean customerWantCondiments() {
        return false;
    }
}
