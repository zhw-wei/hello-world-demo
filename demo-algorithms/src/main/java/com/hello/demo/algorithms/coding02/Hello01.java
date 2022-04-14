package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhw
 * @date 2022/4/13 10:15 PM
 */
@DisplayName("算法02")
public class Hello01 {
    /**
     * leetcode-70 爬楼梯 (类似斐波那契数列)
     *
     * f(1) = 1
     * f(2) = 2
     * f(n) = f(n-1) + f(n-1)
     *
     * 假设你正在爬楼梯，需要n阶你才能到达楼顶
     * 每次你可以爬1或2个台阶，你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定n是一个正整数
     */
    @Test
    @DisplayName("爬楼梯")
    public void test01() {
        Assertions.assertTrue(this.climbStairs(1) == 1);
        Assertions.assertTrue(this.climbStairs(2) == 2);
        Assertions.assertTrue(this.climbStairs(3) == 3);
    }

    //缓存执行结果，避免重复计算
    private Map<Integer, Integer> storeMap = new HashMap<>();

    public int climbStairs(int n) {
        if (n == 1) return 1;//1
        if (n == 2) return 2;//2, 1 1
        if (storeMap.containsKey(n)) {
            return storeMap.get(n);
        } else {
            int result = climbStairs(n - 1) + climbStairs(n - 2);
            storeMap.put(n, result);
            return result;
        }
    }
    /**
     * leetcode-1 两数之和
     *
     * 给定一个数组和一个目标和，从数组中找两个数字相加等于目标和，输出这两个数字的下标
     * 限制：数组内容不能重复
     */
    @Test
    @DisplayName("数组-目标和")
    public void test02() {

        int[] array = {2, 7, 11, 15};
        int target = 9;

        //实现 1
        int[] result0 = this.towSum_0(array, target);
        Assertions.assertTrue(result0[0] == 0);
        Assertions.assertTrue(result0[1] == 1);

        //实现 2
        int[] result1 = this.twoSum_1(array, target);
        Assertions.assertTrue(result1[0] == 0);
        Assertions.assertTrue(result1[1] == 1);

    }

    private int[] towSum_0(int[] array, int target) {

        //创建一个map存储数组值和对应下标
        //循环map的key
        //结果：key index, (target-key) index

        //key: value, value: index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) map.put(array[i], i);

        int index0 = -1;
        int index1 = -1;

        for (Integer value : map.keySet()) {
            int i0 = map.get(value);
            if (map.containsKey(target - value) && map.get(target - value) != i0) {
                index0 = i0;
                index1 = map.get(target - value);
                break;
            }
        }

        //保证结果有序，方便判断
        return new int[]{Math.min(index0, index1), Math.max(index0, index1)};
    }

    private int[] twoSum_1(int[] nums, int target) {

        int index0 = -1;
        int index1 = -1;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];

            int i0 = i;

            if (map.containsKey(target - value)) {
                index0 = i0;
                index1 = map.get(target - value);
                break;
            }

            //限制：下标结果的顺序是反的，举例：（9-2=7，2在前，7在后），在读取2是，7并不在缓存中
            //优点：减少一次放入缓存的循环
            map.put(value, i);
        }

        return new int[]{Math.min(index0, index1), Math.max(index0, index1)};
    }
}
