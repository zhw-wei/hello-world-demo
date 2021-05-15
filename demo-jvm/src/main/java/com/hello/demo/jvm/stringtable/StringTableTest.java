package com.hello.demo.jvm.stringtable;

import org.junit.jupiter.api.Test;

/**
 * @author zhw
 * @date 2021/5/5 2:57 下午
 */
public class StringTableTest {

    @Test
    public void test01(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";//编译期优化

        //只要其中有一个变量，结果就在堆中，相当于在堆空间中new String，具体内容为拼接过
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        //intern()：主动将常量池中还没有的字符串对象放入池中，并返回此对象地址
        String s8 = s6.intern();
        System.out.println(s3 == s8);//true
    }

    @Test
    public void test02(){
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";

        /*
        java8:
        查看字节码 -- 使用了StringBuilder
        s1 + s1的底层实现: s4 = new StringBuilder().append("a").append("b").toString()

        java11:
        查看字节码 -- 使用了本地方法 invokeDynamic<StringConcatFactory.makeConcatWithConstants>
        根据调用的本地方法代码来看，实际上还是用到了StringBuilder

        java8之后，字符串存储不再使用char[]，而是使用byte[]，因为一个char占两个byte，而常用的字符串大多是占用一个byte的字符，所以改用byte[]存储
        */
        String s4 = s1 + s2;

        System.out.println(s3 == s4);//false
    }

    @Test
    public void test03(){
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";

        /*
        class文件反编译查看结果 -- 编译期优化

        如果拼接字符串左右两边都是字符串常量或常量引用，则仍然使用编译期优化，即非StringBuilder方式
         */
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//true
    }

    @Test
    public void test04(){
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);//false

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);//false
    }

    @Test
    public void test05(){
        /*
        根据字节码可以确定创建了两个对象
        1. 字符串常量池中 -- ab
        2. 堆空间 -- new String
        */
        String str = new String("ab");
    }

    @Test
    public void test06(){
        /*
        根据字节码
        java8使用了StringBuilder
        java11未使用StringBuilder
        */
        String str = new String("a") + new String("b");
    }

    @Test
    public void test07(){
        final int MAX_COUNT = 1000*1000;
        final String[] arr = new String[MAX_COUNT];

        Integer[] data = {1,2,3,4,5,6,7,8,9,10};

        long start = System.currentTimeMillis();
        for(int i=0; i<MAX_COUNT; i++){
            //第二种方式耗时较长，但是内存占用更小
//            arr[i] = new String(String.valueOf(data[i%data.length]));
            arr[i] = new String(String.valueOf(data[i%data.length])).intern();
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时: " + (end - start));
        try {
            //使用jvisualvm查看内存信息
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
