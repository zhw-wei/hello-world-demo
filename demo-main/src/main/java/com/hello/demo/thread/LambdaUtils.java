package com.hello.demo.thread;

import java.util.Arrays;
import java.util.List;

public class LambdaUtils {

    public static void sout(){
        List<String> list = Arrays.asList("aa, ", "bb, ", "cc, ", "dd, ", "ee, ", "ff, ");
        list.stream().forEach(System.out::print);
        System.out.println();
    }
    public static void sout_2(){
        List<String> list = Arrays.asList("aa, ", "bb, ", "cc, ", "dd, ", "ee, ", "ff, ");
        list.parallelStream().forEach(System.out::print);
        System.out.println();
    }
}
