package com.hello.demo.jvm.bytecode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看字节码
 * @author zhw
 * @date 2021/9/5 10:10 下午
 */
public class SwitchTest {


    @Test
    public void test01(){
        int i = 0;
        switch (i){
            case 0: i = 0;
            case 2: i = 2; break;
            case 1: i=1;
        }
        System.out.println(i);//2
    }

    @Test
    public void test02(){
        int i=0;
        while(i < 100){
            String s = "hello world";
            i ++;
        }
    }

    @Test
    public void test03(){
        int i=0;
        if(i==0) {
            throw new RuntimeException("hello");
        }
    }

    @Test
    public void test04(){
        int i=0;
        try{
            i = 2;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            i = 3;
            System.out.println("hello");
        }
    }

    @Test
    public String test05(){
        String str = "hello";

        try{
            return str;//hello
        }finally {
            str = "world";
        }
    }

    @Test
    public void test06(){
        //java的范型是伪范型
        List<String> list0 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();

        //类型擦除
        System.out.println(list0.getClass() == list1.getClass());
    }

}
