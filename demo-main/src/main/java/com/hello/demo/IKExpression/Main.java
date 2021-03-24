package com.hello.demo.IKExpression;

public class Main {
    public static void main(String[] args) {
        Main01.test19();
    }

    private static void test01(){
        String str0 = "\"张三\" = \"张三\" and create_date = $DATE($NOW()) and date = $NOW() and 1=1";
        System.out.println(Main02.analysisString(str0));

        System.out.println("==========");

        String str1 = "\"张三\" + \"张三\" + $DATE($NOW()) + $NOW() + 2 * 10";
        System.out.println(Main02.analysisString1(str1));
    }

    private static void test02(){
        Main01.test01();
        Main01.test02();
        Main01.test03();
        Main01.test04();
        Main01.test05();
        Main01.test06();
        Main01.test07();
        Main01.test08();
        Main01.test09();
        Main01.test10();
        Main01.test11();
        Main01.test12();
        Main01.test13();
        Main01.test14();
        Main01.test15();
        Main01.test16();
        Main01.test17();
        Main01.test18();
        Main01.test19();
        Main01.test20();
        Main01.test21();
        Main01.test22();
        Main01.test23();
        Main01.test24();
        Main01.test25();
    }
}
