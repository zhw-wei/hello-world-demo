package com.hello.demo.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证执行 full gc时，用户线程暂停
 *
 * @author zhw
 * @date 2021/8/21 7:58 下午
 */
public class StopTheWorldDemo {

    public static void main(String[] args) {
        new PrintThread().start();
        new WorkThread().start();
    }

    public static class WorkThread extends Thread{
        List<byte[]> list = new ArrayList<>();

        @Override
        public void run() {
            try {
                while (true) {
                    for (int i = 0; i < 1000; i++) {
                        byte[] buffer = new byte[1024];
                        list.add(buffer);
                    }
                    if(list.size() > 1000){
                        list.clear();
                        System.gc();    //会触发 full gc，进而会出现 stw 事件
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public static class PrintThread extends Thread {

        private final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    //每秒打印事件信息
                    long time = System.currentTimeMillis() - startTime;

                    System.out.println(time / 1000 + "." + time % 100);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
