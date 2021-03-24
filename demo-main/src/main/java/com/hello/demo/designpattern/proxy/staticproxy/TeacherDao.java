package com.hello.demo.designpattern.proxy.staticproxy;

public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("teacher dao");
    }
}
