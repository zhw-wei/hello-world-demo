package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 说明： 此文件中的所有排序算法都是生序排序
 * @author zhw
 * @date 2022/4/21 23:13
 */
@DisplayName("算法10")
public class Hello10 {

    /**
     * 冒泡排序
     */
    @Test
    @DisplayName("")
    public void test01() {
        int[] nums = {0, 1, 6, 2, 7, 3, 9, 4, 10, 5};
        System.out.println(Arrays.toString(nums));
        this.sort01(nums);
        System.out.println(Arrays.toString(nums));
    }

    private void sort01(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        //循环数组长度的次数
        //每次外层循环都会把最大的数值放到数组的尾部
        for (int i = 0; i < nums.length; i++) {
            //从第0个元素开始，一次和后面的元素比较
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j + 1] < nums[j]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序：该算法可以看做冒泡排序的优化
     *
     * 1. 找到数组中最大的那个元素
     * 2. 将它和数组中的最后一个元素交换位置
     * 3. 重复步骤2，同时查找最大元素的数组长度逐步减小，最终遍历整个数组
     */
}
