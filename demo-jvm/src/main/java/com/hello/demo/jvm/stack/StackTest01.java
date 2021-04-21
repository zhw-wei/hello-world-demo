package com.hello.demo.jvm.stack;

/**
 * @author: zhaohw
 * @date: 2021.04.21 上午 10:06
 * @description: 查看final和非final参数再jvm字节码中的区别
 */
public class StackTest01 {

    /*
    0 iconst_0
    1 istore_1
    2 sipush 10000
    5 istore_2
    6 ldc #2 <abc>
    8 astore_3
    9 return
     */
    public void test(){

        final int i = 0;
        final int j = 10000;
        final String str = "abc";
    }

    /*
    0 iconst_0
    1 istore_1
    2 sipush 10000
    5 istore_2
    6 ldc #2 <abc>
    8 astore_3
    9 return
     */
    public void test0(){
        int i = 0;
        int j = 10000;
        String str = "abc";
    }
}
