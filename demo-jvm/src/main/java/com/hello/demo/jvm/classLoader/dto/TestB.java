package com.hello.demo.jvm.classLoader.dto;

/**
 * @author: zhaohw
 * @date: 2021/3/24 15:55
 * @description:
 */
public class TestB {
    public void hello(){
        System.out.println("TestB: " + this.getClass().getClassLoader());
    }
}
