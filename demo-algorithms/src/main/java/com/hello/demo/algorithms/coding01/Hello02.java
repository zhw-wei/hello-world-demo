package com.hello.demo.algorithms.coding01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
        Assertions.assertTrue(this.test01_0(str0) == 3);


    }

    public int test01_0(String str) {
        if (Objects.isNull(str) || str.isEmpty()) return 0;

        Set<Character> charSet = new HashSet<>();

        int len = str.length();

        int maxLen = 0; //最大长度
        int startIndex = 0;  //左下标
        int endIndex = 0;    //右下标

        while (endIndex >= len) {

            char c = str.charAt(endIndex);

            if(charSet.contains(c)){
                startIndex ++;
            }

        }

        return maxLen;
    }
}
