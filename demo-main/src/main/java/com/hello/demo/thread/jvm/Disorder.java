package com.hello.demo.thread.jvm;

/**
 * 指令重排
 */
public class Disorder {
    //如果加了修饰符 volatile则不会指令重排
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            one.join();
            other.join();
            String result = "number " + i + ", x = " + x + ", y = " + y;

            System.out.println(result);
            if (x == 0 && y == 0) {
                /*
                执行到此说明出现了指令重排，因为正常来讲无论线程one和other哪一个先执行，都会给ab其中一个赋值为1，此时，xy至少一个为1；
                除非xy赋值在ab赋值之前执行
                此种场景出现几率很小，循环10万次才有可能出现
                 */
                System.out.println(result);
                break;
            }
        }
    }
}
