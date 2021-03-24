package com.hello.demo.jvm.classLoader.dto;

/**
 * @author: zhaohw
 * @date: 2021/3/24 15:55
 * @description:
 */
public class TestA {
    public void hello(){
        System.out.println("TestA: " + this.getClass().getClassLoader());
        TestB testB = new TestB();
        testB.hello();
    }

    public static void main(String[] args) {
        TestA testA = new TestA();
        testA.hello();
    }
}
