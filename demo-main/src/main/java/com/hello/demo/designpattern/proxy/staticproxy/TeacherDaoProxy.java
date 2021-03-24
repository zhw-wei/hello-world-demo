package com.hello.demo.designpattern.proxy.staticproxy;

/**
 * 静态代理，代理对象
 */
public class TeacherDaoProxy implements ITeacherDao{

    private ITeacherDao target;     //目标对象

    public TeacherDaoProxy(ITeacherDao target){
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("teacher dao proxy start");
        target.teach();
        System.out.println("teacher dao proxy end");
    }
}
