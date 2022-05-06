package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zhw
 * @date 2022/5/6 23:28
 */
@DisplayName("算法14")
public class Hello14 {

    /**
     * leetcode-20 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    @Test
    @DisplayName("有效的括号")
    public void test01(){
        String s = "()[]{}";
        Assertions.assertTrue(this.isValid(s));
    }

    private boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Character> map0 = new HashMap<>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        for (int i = 0; i < s.length(); i++) {

            char char0 = s.charAt(i);

            if (char0 == '(' || char0 == '{' || char0 == '[') {
                stack.push(char0);
            } else if (char0 == '}' || char0 == ']' || char0 == ')') {
                if (stack.isEmpty()) return false;

                if (map0.get(char0).charValue() == stack.peek().charValue()) stack.remove();
                else return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * leetcode-415 字符串相加
     *
     * 给定两个字符串形式的非负整数num1 和num2，计算它们的和并同样以字符串形式返回。
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
     */
    @Test
    @DisplayName("字符串相加")
    public void test02(){
        Assertions.assertEquals(this.addStrings("123", "111"), "234");
        Assertions.assertEquals(this.addStrings("456", "77"), "533");
    }

    private String addStrings(String num1, String num2) {

        int maxLen = Math.max(num1.length(), num2.length());

        StringBuilder result = new StringBuilder();
        //按位相加
        int carry = 0;//进位
        for(int i=0; i<maxLen; i++){
            //字符0的ascii等于48，其它依次累加
            int i0 = 0;
            int i1 = 0;

            if(i<num1.length()) i0 = num1.charAt(num1.length()-i-1) - '0';
            if(i<num2.length()) i1 = num2.charAt(num2.length()-i-1) - '0';

            int i2 = i0 + i1 + carry;
            carry = 0;
            if(i2>9) {
                i2 = i2 - 10;
                carry = 1;
            }
            result.append(i2);
        }
        if(carry>0) result.append(carry);

        return result.reverse().toString();
    }
}
