package com.hello.demo.designpattern.proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
        ITeacherDao teacherDao = new TeacherDao();

        ITeacherDao teacherDaoProxy = new TeacherDaoProxy(teacherDao);

        teacherDaoProxy.teach();
    }
}
