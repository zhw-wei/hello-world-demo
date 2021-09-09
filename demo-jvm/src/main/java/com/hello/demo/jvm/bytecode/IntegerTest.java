package com.hello.demo.jvm.bytecode;

/**
 * @author zhw
 * @date 2021/8/31 9:19 下午
 */
public class IntegerTest {

    private String hello;

    public IntegerTest(){}
    public IntegerTest(String hello) {
        this.hello = hello;
    }

    public static void main(String[] args) {

        Integer x = 5;
        int y = 5;
        System.out.println(x == y); //true

        Integer i1  = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);   //true

        Integer j1 = 128;
        Integer j2 = 128;

        //Integer对象赋值实际上是执行了Integer.valueOf方法
        //Integer.IntegerCache类缓存了 [-128 ~ 127] 的数据
        System.out.println(j1 == j2);   //false

        Integer k1 = 128;
        int k2 = 128;
        //此处比较时，实际上执行了Integer.intValue进行拆箱
        System.out.println(k1 == k2);   //true
    }
}
