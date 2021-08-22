package com.hello.demo.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用测试
 * @author zhw
 * @date 2021/8/22 10:29 下午
 */
public class PhantomReferenceDemo {

    /**
     * 当前类对象的声明
     */
    private static PhantomReferenceDemo obj;

    /**
     * 引用队列
     */
    static ReferenceQueue<PhantomReferenceDemo> phantomQueue = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类的finalize()方法");
        obj = this;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new CheckRefQueue();
        //设为守护线程，当程序中没有非守护线程时，守护线程也就执行结束
        thread.setDaemon(true);
        thread.start();

        phantomQueue = new ReferenceQueue<>();
        obj = new PhantomReferenceDemo();

        PhantomReference<PhantomReferenceDemo> phantomRef = new PhantomReference<>(obj, phantomQueue);

        //不可能获取虚引用中的对象
        System.out.println(phantomRef.get());

        //将强引用对象去除
        obj = null;
        //第一次进行GC，由于对象可复活，GC无法回收该对象
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj is null");
        }else{
            System.out.println("obj 可用");
        }

        System.out.println("第二次GC");
        obj = null;
        System.gc();    //一旦将obj对象回收，就会将此虚引用存放到引用队列中
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj is null");
        }else{
            System.out.println("obj 可用");
        }
    }

    public static class CheckRefQueue extends Thread{

        @Override
        public void run() {
            while (true){
                if(phantomQueue != null){
                    PhantomReference<PhantomReferenceDemo> objt = null;

                    try{
                        objt = (PhantomReference<PhantomReferenceDemo>) phantomQueue.remove();
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }

                    if(objt != null) {
                        System.out.println("追踪垃圾回收过程：PhantomReferenceDemo实例被GC了");
                    }
                }

            }
        }
    }
}
