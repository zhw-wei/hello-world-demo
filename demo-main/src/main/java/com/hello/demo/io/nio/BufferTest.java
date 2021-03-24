package com.hello.demo.io.nio;

import java.nio.ByteBuffer;

/**
 * 对缓冲区buffer的常用api进行案例实现
 */
public class BufferTest {

    public static void main(String[] args) {

        //分配一个缓冲区，容量10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        test(buffer);

        //put，往缓冲区中添加数据
        String name = "hello";
        buffer.put(name.getBytes());
        test(buffer);

        //flip，将缓冲区的界限设置为当前位置，并将当前位置设置成0, 切换成可读模式
        buffer.flip();
        test(buffer);

        //get，数据的读取
        char ch = (char)buffer.get();
        System.out.println(ch);
        test(buffer);

        //clear，清除缓冲区中的数据；clear只是初始化位置，并没有清除数据
        buffer.clear();
        ch = (char)buffer.get();
        System.out.println(ch);
        test(buffer);

        //读取数据
        byte[] b = new byte[2];
        buffer.get(b);
        System.out.println(new String(b));
        test(buffer);

        buffer.mark();//标记此刻这个位置
        buffer.reset();//回到标记位置

        buffer.hasRemaining();//判断缓冲区中是否还有元素
        test(buffer);

        test1();

    }

    private static void test1(){
        // 非直接内存
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.isDirect());

        // 直接内存
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(10);
        System.out.println(buffer1.isDirect());
    }

    private static void test(ByteBuffer buffer){
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------");
    }

}
