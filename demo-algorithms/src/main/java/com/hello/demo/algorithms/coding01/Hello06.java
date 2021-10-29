package com.hello.demo.algorithms.coding01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author: zhaohw
 * @date: 2021.10.27 下午 2:50
 */
public class Hello06 {
    /**
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
     * <p>
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * <p>
     * 必须 原地 修改，只允许使用额外常数空间。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-permutation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void test01() {

        Function<int[], String> print = arr -> {
            List<Integer> list = new ArrayList<>();
            for (int i : arr) {
                list.add(i);
            }

            String str = list.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.println(str);
            return str;
        };

        int[] nums = {1, 2, 3};
        this.nextPermutation(nums);
        Assertions.assertTrue(print.apply(nums).equals("1,3,2"));

        int[] nums2 = {1};
        this.nextPermutation(nums2);
        Assertions.assertTrue(print.apply(nums2).equals("1"));

        int[] nums3 = {3, 2, 1};
        this.nextPermutation(nums3);
        Assertions.assertTrue(print.apply(nums3).equals("1,2,3"));

        int[] nums4 = {1, 3, 2};
        this.nextPermutation(nums4);
        Assertions.assertTrue(print.apply(nums4).equals("2,1,3"));
    }

    // TODO: 2021.10.27 待实现
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) return;

        boolean change = false;

        int slow = nums.length - 1;
        int fast = slow - 1;

        flag:
        while (slow >= 0) {
            while (fast >= 0) {

                change = nums[fast] < nums[slow];

                int n = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = n;

                if(change) break flag;

                fast--;
                slow--;
            }

            slow--;
            fast = slow - 1;
        }

        if (!change) Arrays.sort(nums);
    }

    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * https://leetcode-cn.com/problems/longest-valid-parentheses/
     */
    @Test
    public void test02(){
        String str = ")()())";
        Assertions.assertTrue(this.longestValidParentheses(str) == 4);
    }

    /**
     * 用栈模拟一遍，将所有无法匹配的括号的位置全部置1
     * 例如: "()(()"的mark为[0, 0, 1, 0, 0]
     * 再例如: ")()((())"的mark为[1, 0, 0, 1, 0, 0, 0, 0]
     * 经过这样的处理后, 此题就变成了寻找最长的连续的0的长度
     */
    public int longestValidParentheses(String s) {

        return 0;
    }

    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void test03(){
        int[] nums={4,5,6,7,0,1,2};
        Assertions.assertTrue(this.search(nums, 0) == 4);

    }

    public int search(int[] nums, int target) {

        return 0;
    }
}

