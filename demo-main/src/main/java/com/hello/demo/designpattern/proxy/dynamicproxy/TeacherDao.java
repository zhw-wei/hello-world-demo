package com.hello.demo.designpattern.proxy.dynamicproxy;

public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("dynamic teacher dao");
    }

    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }
}
