package com.hello.demo.algorithms.hwTest;

import java.util.HashMap;
import java.util.Map;

public class Test02 {

    int a;
    static int b;

    public void a0(){
        int t = a;
        int x = b;
    }

    public static void b0(){
        int x = b;
    }

    public static void test(int number){
        int i = 1;
        System.out.print(number + "=");
        while (i < number) {
            if (number % i == 0 && i == 1) {
                System.out.print(i + "x");
                number /= i;

            } else if (number % i == 0 && i != 1) {
                System.out.print(i + "x");
                number /= i;
                continue;
            }
            i++;
        }
        System.out.print(i);
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int feb(int i){
        if(i==0) return 0;
        if(i==1) return 1;
        if(map.containsKey(i)) return map.get(i);

        int j = feb(i-1) + feb(i-2);
        map.put(i, j);
        return j;
    }

    public static void main(String[] args) {
        test(100);

    }

}

class A {
    public A() {
        System.out.print("A");
    }
}

class B extends A {
    public B() {
        System.out.print("B");
    }

    public static void main(String[] args) {
        B b = new B();
    }
}
