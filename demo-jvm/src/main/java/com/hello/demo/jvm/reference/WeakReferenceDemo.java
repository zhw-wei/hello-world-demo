package com.hello.demo.jvm.reference;

import lombok.Data;

import java.lang.ref.WeakReference;

/**
 * 弱应用
 * @author zhw
 * @date 2021/8/22 6:15 下午
 */
public class WeakReferenceDemo {

    @Data
    public static class User{

        private Integer id;
        private String name;

        public User(Integer id, String name){
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        WeakReference<User> userWeakReference = new WeakReference<>(new User(1, "hello"));

        System.out.println(userWeakReference.get());

        System.gc();//执行垃圾回收

        System.out.println(userWeakReference.get());

    }
}
