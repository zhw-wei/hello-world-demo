package com.hello.demo.jvm.register;

public class Main {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;

        Main main = new Main();
        main.test01();
    }

    private void test01(){
        System.out.println("test01");
        test02();
    }
    private void test02(){
        System.out.println("test01");
    }

    private void test03(){
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        //变量c使用之前已销毁的变量b占据的slot的位置
        int c = a + 1;
    }
}
