package com.hello.demo.algorithms.coding01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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
     *
     * 中位数，又称中点数，中值。中位数是按顺序排列的一组数据中居于中间位置的数，
     *        即在这组数据中，有一半的数据比他大，有一半的数据比他小
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
    private float test02_0(int[] array0, int[] array1){

        return 0;
    }

    /**
     * 给定一个字符串，输出最长的回文子串。回文串指的是正的读和反的读是一样的字符串，例如 “aba”，”ccbbcc”
     */
    @Test
    @DisplayName("最长回文串")
    public void test03(){
        String str0 = "babad";
        String str1 = "ccbbccaa";
        Assertions.assertTrue(this.test03_0(str0).equals("bab"));

    }

    //扩散算法
    public String test03_0(String str){
        if(Objects.isNull(str) || str.isEmpty()) return str;
        if(str.length() == 1) return str;

        int len = str.length();
        //所有的回文串
        List<String> list = new ArrayList<>();

        //返回最长的回文串
        return list.stream().sorted(Comparator.comparing(String::length)).findFirst().get();
    }
}
