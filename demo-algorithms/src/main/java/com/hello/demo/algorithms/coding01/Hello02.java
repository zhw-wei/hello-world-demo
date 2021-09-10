package com.hello.demo.algorithms.coding01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhaohw
 * @date: 2021.09.08 下午 6:06
 */
@DisplayName("算法02")
public class Hello02 {

    /**
     * 给定一个字符串，找到没有重复字符的最长子串，返回它的长度。
     */
    @DisplayName("子串长度")
    @Test
    public void test01() {

        String str0 = "abc";
        String str1 = "abcabca";
        Assertions.assertTrue(this.test01_0(str0) == 3, () -> "结果值是" + this.test01_0(str0));
        Assertions.assertTrue(this.test01_0(str1) == 3);

    }

    private int test01_0(String str) {
        if (Objects.isNull(str) || str.isEmpty()) return 0;

        Set<Character> charSet = new HashSet<>();

        int len = str.length();

        int maxLen = 0; //最大长度
        //左右下标不会同时移动
        //右下标值重复时，右下标停止移动，左下标开始右移，直至不再存在重复
        int startIndex = 0;  //左下标
        int endIndex = 0;    //右下标

        while (endIndex < len && startIndex < len) {

            char c = str.charAt(endIndex);
            if (charSet.contains(c)) {
                startIndex++;
                charSet.remove(c);
            } else {
                charSet.add(c);
                endIndex++;
                maxLen = Math.max(maxLen, (endIndex - startIndex));
            }
        }

        return maxLen;
    }

    /**
     * 已知两个有序数组，找到两个数组合并后的中位数
     * <p>
     * 中位数，又称中点数，中值。中位数是按顺序排列的一组数据中居于中间位置的数，
     * 即在这组数据中，有一半的数据比他大，有一半的数据比他小
     */
    @Test
    @DisplayName("数组中位数")
    public void test02() {
        int[] array0 = {1, 3};
        int[] array1 = {2};
        Assertions.assertTrue(this.test02_0(array0, array1) == 2.0);

        int[] array2 = {1, 2};
        int[] array3 = {3, 4};
        Assertions.assertTrue(this.test02_0(array0, array1) == 2.5);

    }

    // TODO: 2021.09.09 待实现
    private float test02_0(int[] array0, int[] array1) {

        return 0;
    }

    /**
     * 给定一个字符串，输出最长的回文子串。回文串指的是正的读和反的读是一样的字符串，例如 “aba”，”ccbbcc”
     */
    @Test
    @DisplayName("最长回文串")
    public void test03() {
        String str0 = "babad";
        String str1 = "ccbbccaa";
        Assertions.assertTrue(this.test03_0(str0).length() == 3);
        Assertions.assertTrue(this.test03_0(str1).equals("ccbbcc"));

    }

    //扩散算法
    public String test03_0(String str) {
        if (Objects.isNull(str) || str.isEmpty()) return str;
        if (str.length() == 1) return str;

        int len = str.length();
        //所有的回文串
        List<String> list = new ArrayList<>();

        //从某一点向左右扩散，判断是否是回文串
        BiFunction<Integer, Integer, String> rightString = (start0, start1) -> {

            while (start0 >= 0 && start1 < len
                    && str.charAt(start0) == str.charAt(start1)) {
                start0--;
                start1++;
            }
            return str.substring(start0 + 1, start1);
        };

        for (int i = 0; i < len; i++) {
            //奇数对称
            String str0 = rightString.apply(i, i);
            if (str0.length() > 1) list.add(str0);

            //偶数对称
            String str1 = rightString.apply(i, i + 1);
            if (str1.length() > 1) list.add(str1);
        }

        System.out.println(list);

        //返回最长的回文串
        return list.stream().max(Comparator.comparing(var0 -> var0.length())).orElse("");
    }

    // TODO: 2021.09.10 马拉车算法
    private String test03_1(String str) {

        return null;
    }

    @Test
    @DisplayName("判断是否是回文数")
    public void test04() {
        int x0 = 121;
        Assertions.assertTrue(this.isPalindrome(x0));

        int x1 = -121;
        Assertions.assertFalse(this.isPalindrome(x1));

        int x2 = 10;
        Assertions.assertFalse(this.isPalindrome(x2));

    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;

        String str = String.valueOf(x);
        int len = str.length();

        int midLen = (len + len % 2) / 2;

        String first = str.substring(0, midLen);
        String second = str.substring(len - midLen);

        return first.equals(new StringBuilder(second).reverse().toString());
    }


    public boolean isPalindrome_2(int x) {
        StringBuilder sb = new StringBuilder().append(x);
        String str0 = sb.toString();
        String str1 = sb.reverse().toString();

        return str0.equals(str1);
    }

    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     */
    @Test
    @DisplayName("自定义正则表达式")
    public void test05() {

        String s0 = "aa";
        String p0 = "a";
        Assertions.assertFalse(this.isMatch(s0, p0));

        String s1 = "aa";
        String p1 = "a*";
        Assertions.assertTrue(this.isMatch(s1, p1));

        String s2 = "ab";
        String p2 = "*.";
        Assertions.assertTrue(this.isMatch(s2, p2));

        String s3 = "aab";
        String p3 = "c*a*b";
        Assertions.assertTrue(this.isMatch(s3, p3));
    }

    // TODO: 2021.09.10
    public boolean isMatch(String s, String p) {

        return true;
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * f(x) = array[i]
     * result = min(f(i), f(j)) * (j-i)
     * <p>
     * i,j处于数组的两端，向内移动，(j-i)会逐渐的变小，因此需要尽可能地保证 min(f(i),f(j)) 大，所以让值小的下标向内移动
     */
    @Test
    @DisplayName("容积")
    public void test06() {
        int[] array = {1, 1};
        Assertions.assertTrue(this.maxArea(array) == 1);

        int[] array1 = {4, 3, 2, 1, 4};
        Assertions.assertTrue(this.maxArea(array1) == 16);

        int[] array2 = {1, 2, 1};
        Assertions.assertTrue(this.maxArea(array2) == 2);

    }

    public int maxArea(int[] height) {
        if (height.length == 0) return 0;

        int i = 0;
        int j = height.length - 1;

        int max = 0;
        while (i <= j) {

            int iv = height[i];
            int jv = height[j];

            max = Math.max(max, Math.min(iv, jv) * (j - i));
            if (jv >= iv) i++;
            else j--;
        }

        return max;
    }

    @Test
    @DisplayName("最长公共前缀")
    public void test07() {
        String[] str = {"flower", "flow", "flight"};
        Assertions.assertTrue(this.longestCommonPrefix(str).equals("fl"));

        String[] str1 = {"a"};
        Assertions.assertTrue(this.longestCommonPrefix(str1).equals("a"));

        String[] str2 = {"aa"};
        Assertions.assertTrue(this.longestCommonPrefix(str2).equals("aa"));

    }

    public String longestCommonPrefix(String[] strs) {

        int minLen = Stream.of(strs).map(String::length).min(Integer::compareTo).get();

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < minLen; i++) {
            set.clear();
            for (String s : strs) {
                set.add(s.charAt(i));
                if (set.size() != 1) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0].substring(0, minLen);
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
     * 请你找出所有和为 0 且不重复的三元组。
     *
     * 答案中不可以包含重复的三元组
     */
    @Test
    @DisplayName("和为0的不重复三元组")
    public void test08() {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        System.out.println(this.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        if(set.size() < 3 || set.stream().allMatch(var0 -> var0>=0)
            || set.stream().allMatch(var1 -> var1 <= 0)) return new ArrayList<>();

        int total = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Integer num : set) {
            int next = total - num;
            for (Integer num2 : set) {
                int num3 = next - num2;

                if(set.contains(num3) && !num.equals(num2)
                        && !num.equals(num3) && !num2.equals(num3)) {
                    List<Integer> list = Arrays.asList(num, num2, num3);
                    if(result.stream().noneMatch(var0 -> var0.containsAll(list)))
                        result.add(list);
                }
            }
        }

        return result;
    }
}
