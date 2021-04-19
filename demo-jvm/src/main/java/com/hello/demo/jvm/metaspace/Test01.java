package com.hello.demo.jvm.metaspace;

/**
 * @author zhw
 * @date 2021/4/19 10:58 下午
 */
public class Test01 {
    public static void main(String[] args) {
        Test02 test02 = null;//值为null
        test02.hello(); //静态方法可以被访问
        System.out.println(test02.hello);//静态属性可以被访问

        System.out.println("hello world test01");
    }

}
