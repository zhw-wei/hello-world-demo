package com.hello.demo.java11;

/**
 * https://blog.csdn.net/weixin_38055381/article/details/82865385
 * @author: zhaohw
 * @date: 2021/4/8 15:52
 * @description: java11新特性
 */
public class HelloJava11 {

    public static void main(String[] args) {
        HelloJava11 java11 = new HelloJava11();

        java11.test01();
        java11.test02();
    }

    /**
     * 本地变量类型推断
     */
    private void test01(){
        var string = "hello world";
        System.out.println(string);
    }

    /**
     * 字符串加强，新增字符串处理方法
     */
    private void test02(){
        //判断字符串是否是空白
        System.out.println(" ".isBlank());
        //去除首位空格
        System.out.println(" string ".strip());
        //去除尾部空格
        System.out.println(" string ".stripTrailing());
        //去除首部空格
        System.out.println(" string ".stripLeading());
        //复制字符串
        System.out.println(" string ".repeat(3));
        //行数统计
        System.out.println("A\nB\nC".lines().count());
    }
}
