package com.hello.demo.jvm.reference.cache;

import com.hello.demo.jvm.reference.ReferenceCache;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 测试代码
 *
 * @author: zhaohw
 * @date: 2021.08.23 上午 11:32
 */
public class ReferenceTest {

    @Data
    @AllArgsConstructor
    public static class User {
        private Integer id;
        private String name;
    }

    public static void main(String[] args) throws InterruptedException {
        //软引用垃圾回收场景较为苛刻，使用弱引用测试缓存代码是否使用了非强引用
        test01();
        System.out.println("==================");
        //软引用缓存 value 不要使用字符串，否则不会被垃圾回收
        test02();
        System.out.println("==================");
        test03();
    }

    private static void test01() throws InterruptedException {
        ReferenceCache<String, User> cache = new WeakReferenceCache<>();
        cache.put("hello01", new User(1, "hello01"));
        cache.put("hello02", new User(2, "hello02"));
        cache.put("hello03", new User(3, "hello03"));

        for (String key : cache.keySet()) {
            System.out.println(cache.get(key));
        }

        System.out.println("GC ------");
        System.gc();

        for (String key : cache.keySet()) {
            System.out.println(cache.get(key));
        }
    }

    private static void test02() throws InterruptedException {
        ReferenceCache<String, String> cache = new WeakReferenceCache<>();
        cache.put("hello01", "hello01");
        cache.put("hello02", "hello02");
        cache.put("hello03", "hello03");

        for (String key : cache.keySet()) {
            System.out.println(cache.get(key));
        }

        System.out.println("GC -----");
        System.gc();

        for (String key : cache.keySet()) {
            System.out.println(cache.get(key));
        }
    }

    private static void test03() throws InterruptedException {
        ReferenceCache<String, String> cache = new WeakReferenceCache<>();
        cache.put("hello01", "hello001");
        cache.put("hello02", "hello002");
        cache.put("hello03", "hello003");

        for (String key : cache.keySet()) {
            System.out.println(cache.get(key));
        }

        System.out.println("GC -----");
        System.gc();

        for (String key : cache.keySet()) {
            System.out.println(cache.get(key));
        }
    }
}
