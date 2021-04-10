package com.hello.demo.jvm.stack;

/**
 * 堆空间
 * @author zhw
 * @date 2021/4/10 10:34 下午
 */
public class HeapSpaceInitial {

    /**
     * 默认情况下，初始内存大小：物理电脑内存大小/64，最大内存大小：物理电脑内存大小/4
     * java.lang.Runtime表示虚拟机运行时数据区
     * 启动增加参数，打印出垃圾回收日志：-XX:+PrintGCDetails
     */
    public static void main(String[] args) {
        //返回java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;

        //返回java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms : " + initialMemory + "m");
        System.out.println("-Xmx : " + maxMemory + "m");

        System.out.println("系统内存大小为：" + initialMemory * 64 / 1024 + "G");
        System.out.println("系统内存大小为：" + maxMemory * 4 / 1024 + "G");
    }
}
