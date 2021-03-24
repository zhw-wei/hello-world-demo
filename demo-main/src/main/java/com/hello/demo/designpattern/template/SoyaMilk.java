package com.hello.demo.designpattern.template;

public abstract class SoyaMilk {

    public final void make(){

        select();
        if(customerWantCondiments()) {
            addCondiment();
        }
        soak();
        beat();
    }

    private void select(){
        System.out.println("start select");
    }

    public abstract void addCondiment();

    private void soak(){
        System.out.println("start soak");
    }

    private void beat(){
        System.out.println("start beat");
    }

    //钩子方法
    public Boolean customerWantCondiments(){
        return true;
    }
}
