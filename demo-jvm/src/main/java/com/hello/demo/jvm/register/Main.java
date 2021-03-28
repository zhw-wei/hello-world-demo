package com.hello.demo.jvm.register;

public class Main {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;

        Main main = new Main();
        main.test01();

        main.test04();
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

    private void test04(){
        //第一类问题
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;

        //第二类问题
        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        //第三类问题
        int i7 = 10;
        i7 = i7 ++;
        System.out.println(i7);//10

        int i8 = 10;
        i8 = ++i8;
        System.out.println(i8);//11

        //第四类问题
        int i9 = 10;
        int i10 = i9++ + ++i9;
        System.out.println(i10);//22
    }
}
