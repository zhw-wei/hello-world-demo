package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zhw
 * @date 2022/5/6 21:49
 */
@DisplayName("算法13")
public class Hello13 {

    /**
     * leetcode-136 只出现一次的数字
     * <p>
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * <p>
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     */
    @Test
    @DisplayName("只出现一次的数字")
    public void test01() {

        int[] nums = {2, 2, 1};
        Assertions.assertEquals(this.singleNumber(nums), 1);

    }

    private int singleNumber(int[] nums) {

        int result = 0;

        //0 ^ m = m
        //m ^ n ^ m = n
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }

    /**
     * leetcode-338 比特位计数
     * <p>
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     */
    @Test
    @DisplayName("比特位计数")
    public void test02() {
        // 0,1,2
        //[0,1,1]
        System.out.println(Arrays.toString(this.countBits(2)));
        System.out.println(Arrays.toString(this.countBits2(2)));
        // 0,1,2,3,4,5
        //000,001,010,011,100,101
        //[0,1,1,2,1,2]
        System.out.println(Arrays.toString(this.countBits(5)));
        System.out.println(Arrays.toString(this.countBits2(5)));
    }

    private int[] countBits(int n) {
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int j = i;
            while (j > 0) {
                //加的优先级高于与运算
                nums[i] = nums[i] + (j & 1);
                j = j >> 1;
            }
        }

        return nums;
    }

    private int[] countBits2(int n) {
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            //i>>1, 左移一位的数，101 >> 1 = 10
            nums[i] = nums[i >> 1] + (i & 1);
        }

        return nums;
    }

    /**
     * leetcode-461 汉明距离
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     */
    @Test
    @DisplayName("汉明距离")
    public void test03() {
        /*
         * 1   (0 0 0 1)
         * 4   (0 1 0 0)
         */
        Assertions.assertEquals(this.hammingDistance(1, 4), 2);
        //0001, 0011
        Assertions.assertEquals(this.hammingDistance(1, 3), 1);

        //1011101 93
        //1001001 73
        Assertions.assertEquals(this.hammingDistance(93, 73), 2);

    }

    private int hammingDistance(int x, int y) {

        int z = x ^ y;

        int i = 0;
        while (z > 0) {
            i += (z & 1) == 1 ? 1 : 0;
            z = z >> 1;
        }
        return i;
    }
}
