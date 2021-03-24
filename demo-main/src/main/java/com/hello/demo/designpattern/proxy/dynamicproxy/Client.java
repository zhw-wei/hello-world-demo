package com.hello.demo.designpattern.proxy.dynamicproxy;

public class Client {
    public static void main(String[] args) {
        ITeacherDao target = new TeacherDao();

        ITeacherDao teacherDao = (ITeacherDao) new ProxyFactory(target).getProxyInstance();

        teacherDao.teach();
        teacherDao.sayHello("world");
    }
}
