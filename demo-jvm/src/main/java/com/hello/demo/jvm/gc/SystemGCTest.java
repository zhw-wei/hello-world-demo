package com.hello.demo.jvm.gc;

/**
 * -XX:+PrintCommandLineFlags
 * @author zhw
 * @date 2021/8/17 10:38 下午
 */
public class SystemGCTest {

    public static void main(String[] args) {

        new SystemGCTest();

        //提醒jvm的垃圾收集器执行gc，但是不确定是否马上执行
        System.gc();

        //强制调用使用引用的对象的finalize()方法
        System.runFinalization();

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        //触发GC是会输出
        System.out.println("SystemGCTest 重新了 finalize()");
    }
}
