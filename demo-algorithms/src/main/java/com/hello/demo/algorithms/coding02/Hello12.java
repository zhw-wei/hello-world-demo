package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 位运算相关
 *
 * @author zhw
 * @date 2022/5/5 23:28
 */
@DisplayName("算法12")
public class Hello12 {

    @Test
    @DisplayName("二进制计算")
    public void test01() {

        /*
         * 与运算
         * 应用场景： 对某些位清 0，或者保留某些位
         * 1.清零（将一个单元与0进行位与运算结果为零）
         * 2.取一个数指定位为0
         * 3.判断奇偶性
         */
        //100 & 110 = 100
        Assertions.assertEquals(4 & 6, 4);
        //101 & 110 = 100
        Assertions.assertEquals(5 & 6, 4);
        //100 & 101 = 100
        Assertions.assertEquals(4 & 5, 4);
        //101 & 111 = 101
        Assertions.assertEquals(5 & 7, 5);
        //100 & 001 = 0, 偶数
        Assertions.assertEquals(4 & 1, 0);
        //101 & 001 = 1, 奇数
        Assertions.assertEquals(5 & 1, 1);

        /*
         * 或运算
         * 应用场景：将某些位置1，或者保留某些位
         */
        //100 | 110 = 110
        Assertions.assertEquals(4 | 6, 6);
        //101 | 110 = 111
        Assertions.assertEquals(5 | 6, 7);
        //100 | 101 = 101
        Assertions.assertEquals(4 | 5, 5);
        //101 | 111 = 111
        Assertions.assertEquals(5 | 7, 7);

        /*
         * 非运算
         * 应用场景：对参与运算的二进制位取反
         */
        //~001 = 000
        Assertions.assertEquals(~1 + 1, -1);
        //
        Assertions.assertEquals(~-1 + 1, 1);
        //~011 =
        Assertions.assertEquals(~3 + 1, -3);
        //
        Assertions.assertEquals(~-3 + 1, 3);

        /*
         * 抑或运算：相同为0，相异为1
         * 应用场景：同一个数两次取反，结果不变
         */
        //101 ^ 011 ^ 011 = 101 ^ 000 = 101
        Assertions.assertEquals(5 ^ 3 ^ 3, 5);
        //不使用中间参数，交换两数的值
        int a = 5;
        int b = 3;
        a = a ^ b;
        b = a ^ b; // a^b^b = a
        a = a ^ b; // a^b^a = b
        Assertions.assertEquals(a, 3);
        Assertions.assertEquals(b, 5);

        a = 5;
        b = 3;
        a = a + b;
        b = a - b;
        a = a - b;
        Assertions.assertEquals(a, 3);
        Assertions.assertEquals(b, 5);

        /*
         * 有符号移动主要考虑正数
         */

        //有符号右移
        //101 >> 2 = 1 ==> 5/4
        Assertions.assertEquals(5 >> 2, 1);
        //1010 >> 2 = 10 = 2 ==> 10/4
        Assertions.assertEquals(10 >> 2, 2);

        //有符号左移
        //101 << 2 = 10100 = 12 ==> 5 * 4
        Assertions.assertEquals(5 << 2, 20);
        //1010 << 2 = 101000 = 40 ==> 10 * 4
        Assertions.assertEquals(10 << 2, 40);

        //无符号右移
        Assertions.assertEquals(5 >>> 2, 1);
        Assertions.assertEquals(10 >>> 2, 2);

        //无符号左移 java不支持

    }
}
