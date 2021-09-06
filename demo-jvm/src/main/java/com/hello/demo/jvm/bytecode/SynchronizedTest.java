package com.hello.demo.jvm.bytecode;

import org.junit.jupiter.api.Test;

/**
 * @author: zhaohw
 * @date: 2021.09.06 下午 4:47
 */
public class SynchronizedTest {

    private Object obj0 = new Object();

    private static Object obj1 = new Object();

    @Test
    public void test01() {

        int i = 0;
        synchronized (obj0) {
            i++;
        }

        System.out.println(i);
    }

    @Test
    public void test02() {
        int i = 0;
        synchronized (SynchronizedTest.class) {
            ++i;
        }

        System.out.println(i);
    }

    @Test
    public void test03() {
        int i = 0;
        synchronized (obj1) {
            i++;
        }

        System.out.println(i);
    }

    @Test
    public synchronized void test04(){
        int i = 1;
        System.out.println(i);
    }
}
