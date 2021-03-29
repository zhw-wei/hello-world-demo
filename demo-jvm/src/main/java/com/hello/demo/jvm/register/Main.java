package com.hello.demo.jvm.register;

public class Main {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;

        Main main = new Main();
        main.test01();

        main.test04();
        main.test08();
        main.test09();
    }

    private void test01() {
        System.out.println("test01");
        test02();
    }

    private void test02() {
        System.out.println("test01");
    }

    private void test03() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        //变量c使用之前已销毁的变量b占据的slot的位置
        int c = a + 1;
    }

    private void test04() {
        //第一类问题
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;
    }

    public void test05() {
        /*
         0 bipush 10
         2 istore_1
         3 iload_1
         4 iinc 1 by 1  ==> inic 等同于执行了iload_1、istore_1两条命令，执行到此处时实际上做了两次iload_1
         7 istore_2 ==> 取到的结果时第一次iload_1的值
        */
        //第二类问题
        int i3 = 10;
        int i4 = i3++;

        /*
         8 bipush 10
        10 istore_3
        11 iinc 3 by 1 ==> iload_3、istore_3
        14 iload_3  ==> 此处取到的结果是加一后的结果
        15 istore 4
        17 return
         */
        int i5 = 10;
        int i6 = ++i5;
    }

    public void test06() {
        //第三类问题
        int i7 = 10;
        i7 = i7++;  //10

        int i8 = 10;
        i8 = ++i8;  //11
    }

    public void test07() {
        //第四类问题
        int i9 = 10;
        int i10 = i9++ + ++i9;//22
    }

    public void test08(){
        int i10 = 10;
        i10 = i10 + 1;

        int i11 = 10;
        i11 += i11;
    }

    public void test09(){
        synchronized (Main.class) {
            int i = 10;
            i++;
        }
    }
}
