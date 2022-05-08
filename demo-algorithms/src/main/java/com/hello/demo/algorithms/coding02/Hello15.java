package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author zhw
 * @date 2022/5/8 17:11
 */
@DisplayName("算法15")
public class Hello15 {

    /**
     * leetcode-53 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * <p>
     * 动态规划思想
     */
    @Test
    @DisplayName("最大子数组和")
    public void test01() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assertions.assertEquals(this.maxSubArray(nums), 6);
        Assertions.assertEquals(this.maxSubArray2(nums), 6);
    }

    private int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            //状态转移公式
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            //result保存dp[i]的最大值
            if (dp[i] > result) result = dp[i];
        }
        return result;
    }

    private int maxSubArray2(int[] nums) {
        int pre = nums[0];
        int result = pre;

        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            if (pre > result) result = pre;
        }
        return result;
    }

    /**
     * leetcode-121 买卖股票的最佳时机
     * <p>
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     */
    @Test
    @DisplayName("买卖股票的最佳时机")
    public void test02() {
        int[] nums = {7, 1, 5, 3, 6, 4};
        Assertions.assertEquals(this.maxProfit(nums), 5);
    }

    private int maxProfit(int[] prices) {

        int minprice = Integer.MAX_VALUE;//最低股票价格
        int maxprofit = 0;//最大利润
        for (int i = 0; i < prices.length; i++) {
            //股票相对最低点肯定是最佳买入时间
            //每次一次赋值实际上就是一次买入
            if (prices[i] < minprice) {
                minprice = prices[i];

                //利润最高就是最佳卖出点
                //每一次比较实际上就是一次卖出
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    /**
     * leetcode-470 用 Rand7() 实现 Rand10()
     * <p>
     * 给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
     * <p>
     * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random()方法。
     * <p>
     * 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
     */
    @Test
    @DisplayName("用 Rand7() 实现 Rand10()")
    private void test03() {
        System.out.println(rand10());
    }

    /*
     定理：若rand_n()能等概率生成1到n的随机整数，则有(rand_n() - 1) * n + rand_n()能等概率生成1到n * n的随机整数。

     解释：
     rand7() 能等概率生成1~7,
　　　rand7()-1 能等概率生成0~6,
　　　(rand7()-1)*7 能等概率生成{0, 7, 14, 21, 28, 35, 42},
　　　(rand7()-1)*7+rand7() 能等概率生成1~49
     */
    private int rand10() {
        while (true) {
            int x = rand7();
            int num = (x - 1) * 7 + rand7();        //rand7()每次调用的值都不同，故不要用变量来存
            if (num <= 40) return num % 10 + 1;     //大于 40 拒绝采样，

            x = num - 40;                           //此时 x = rand9()
            num = (x - 1) * 7 + rand7();            // 8 * 7 + 7 = 63
            if (num <= 60) return num % 10 + 1;     //大于 60 拒绝采样

            x = num - 60;                           //此时 x = rand3()
            num = (x - 1) * 7 + rand7();            // 2 * 7 + 7 = 21
            if (num <= 20) return num % 10 + 1;     //大于 20 拒绝采样
        }
    }

    //rand7是已实现的方法
    private int rand7() {
        return 0;
    }
}
