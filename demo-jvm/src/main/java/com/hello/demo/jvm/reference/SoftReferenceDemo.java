package com.hello.demo.jvm.reference;

import lombok.Data;

import java.lang.ref.SoftReference;

/**
 * 软引用
 * @author zhw
 * @date 2021/8/22 5:45 下午
 */
public class SoftReferenceDemo {

    @Data
    public static class User{
        private Integer id;
        private String name;

        public User(Integer id, String name){
            this.id = id;
            this.name = name;
        }
    }

    /**
     * 启动是置顶参数: -Xms10m -Xmx10m -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {

        //定义软引用
        SoftReference<User> userSoftReference = new SoftReference<>(new User(1, "hello"));
        /*
        //上面一行代码等同于下面的三行代码，注意user对象一定要设为null，否则软应用不起作用
        User user = new User(1, "hello");
        SoftReference<User> userSoftReference = new SoftReference<>(user);
        user = null;    //取消强引用
         */

        //从软引用中获得引用对象
        System.out.println(userSoftReference.get());

        System.gc();
        //垃圾回收后获得软引用中的对象
        System.out.println("after gc: " + userSoftReference.get());

        try{
            //让系统认为内存资源紧张
            byte[] b = new byte[1024 * 1024 * 7];   //5m
        }catch (Throwable th){
            th.printStackTrace();
        }finally {
            System.out.println(userSoftReference.get());
        }
    }
}
