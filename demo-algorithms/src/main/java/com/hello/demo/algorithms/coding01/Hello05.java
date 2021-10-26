package com.hello.demo.algorithms.coding01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author: zhaohw
 * @date: 2021.10.26 下午 3:38
 */
public class Hello05 {
    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    @DisplayName("有序数组，删除重复元素")
    public void test01() {

        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};//0,1,2,3,4
        Assertions.assertTrue(this.removeDuplicates(nums) == 5);
    }

    public int removeDuplicates(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        //快慢指针
        int slow = 1, fast = 1;

        while (fast < nums.length) {
            //数组左移
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    @DisplayName("移除元素")
    public void test02() {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        Assertions.assertTrue(this.removeElement(nums, 2) == 5);

    }

    public int removeElement(int[] nums, int val) {
        if (Objects.isNull(nums) || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    /**
     * 实现 strStr() 函数。
     * <p>
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
     * 如果不存在，则返回  -1 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    @DisplayName("strStr函数")
    public void test03() {
        String haystack = "hello", needle = "ll";
        Assertions.assertTrue(this.strStr(haystack, needle) == 2);
    }

    // TODO: 2021.10.26 暴力解法不可取，要使用kmp算法
    public int strStr(String haystack, String needle) {
        if (Objects.isNull(haystack) || Objects.isNull(needle)) return -1;
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty() && !needle.isEmpty()) return -1;

        int index = -1;
        flag:
        for (int i = 0; i < haystack.length(); i++) {
            //根据needle首字符找到开始节点
            if (haystack.charAt(i) == needle.charAt(0)) {
                for (int j = i, k = 0; j < haystack.length() && k < needle.length(); j++, k++) {
                    if (haystack.charAt(j) != needle.charAt(k)) break;
                    if (k == needle.length() - 1) {
                        index = i;
                        break flag;
                    }
                }

            }
        }

        return index;
    }

    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * <p>
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * <p>
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/divide-two-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    @DisplayName("")
    public void test04() {
        int dividend = 10;
        int divisor = 3;
        Assertions.assertTrue(this.divide(dividend, divisor) == 3);
    }

    // TODO: 2021.10.26 执行失败了，边界值有待处理
    public int divide(int dividend, int divisor) {
        if (divisor == 1) return dividend;
        if (dividend == -1) return -dividend;
        if (dividend == 0 || dividend == 0) return 0;

        boolean bigThanZero = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);

        if (dividend < 0) dividend = -dividend;
        if (divisor < 0) divisor = -divisor;

        int res = 0;
        while (dividend > divisor) {
            res++;
            dividend -= divisor;
        }
        if (!bigThanZero) res = -res;
        return res;
    }

    /**
     * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     *
     * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    @DisplayName("")
    public void test05(){

    }

    public List<Integer> findSubstring(String s, String[] words) {

        return null;
    }
}
