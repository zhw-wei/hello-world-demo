package com.hello.demo.designpattern.proxy.cglibproxy;

public class Client {
    public static void main(String[] args) {
        TeacherDao teacherDao = (TeacherDao) new ProxyFactory(new TeacherDao()).getProxyInstance();
        teacherDao.teach();
    }
}
