package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhw
 * @date 2022/4/16 22:13
 */
@DisplayName("算法02")
public class Hello02 {

    /**
     * leetcode-283
     * <p>
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * <p>
     * 最简单的算法是：冒泡
     */
    @Test
    @DisplayName("移动零")
    public void test01() {

        int[] nums = {0, 1, 0, 3, 12};
        this.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {0, 1, 0, 3, 12};
        this.moveZeroes_2(nums2);
        System.out.println(Arrays.toString(nums2));

    }

    //单向循环 + 双指针 + 复制
    private void moveZeroes(int[] nums) {
        int i0 = 0;
        int i1 = nums.length - 1;

        int[] nums2 = new int[nums.length];
        for (int num : nums) {
            if (num == 0) {
                nums2[i1] = num;
                i1--;
            } else {
                nums2[i0] = num;
                i0++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums2[i];
        }
    }

    //单向循环
    //类似于冒泡排序
    private void moveZeroes_2(int[] nums) {
        /*
         * index是假设值为0的数字的下标
         * 开始循环时，如果第一个值不是0，实际上就是赋值给自身，第二个、第三个、。。。都是如此
         */
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int n = nums[i];
                nums[i] = nums[index];
                nums[index] = n;
                index++;
            }
        }
    }

    /**
     * leetcode-448
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     */
    @Test
    @DisplayName("找到所有数组中消失的数字")
    public void test02() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(this.findDisappearedNumbers(nums));
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(this.findDisappearedNumbers_2(nums1));
        int[] nums2 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(this.findDisappearedNumbers_3(nums2));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        //使用额外空间
        //根据下标判断是否有值，0表示有值
        int[] nums2 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) nums2[i] = i + 1;

        for (int num : nums) nums2[num - 1] = 0;

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (num != 0) list.add(num);
        }
        return list;
    }

    public List<Integer> findDisappearedNumbers_2(int[] nums) {

        //不使用额外空间
        /*
         * 原数组充当哈希表, 把值对应下标的数变成负数
         *
         * 可以使用下标的原因是：最大值是1～n，减一之后就可以使用为nums的下标(n-1)，
         *      把下标(n-1)对应的值变成负数(val)，下标(n-1)对应的值未变成负数说明没有这个值(n)
         */
        for (int val : nums) {
            int index = Math.abs(val) - 1;
            int indexVal = Math.abs(nums[index]);
            nums[index] = indexVal * -1;
        }

        List<Integer> list = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            if(nums[index] > 0) list.add(index + 1);
        }

        return list;
    }

    public List<Integer> findDisappearedNumbers_3(int[] nums) {
        //原数组充当哈希表，把值对应下表的值变成比n大的数，减少Math.abs三目运算
        for (int val : nums) {
            int index = (val - 1) % nums.length;
            nums[index] = nums[index] + nums.length;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] <= nums.length) list.add(i + 1);
        }
        return list;
    }
}
