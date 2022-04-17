package com.hello.demo.algorithms.coding02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author zhw
 * @date 2022/4/17 16:38
 */
@DisplayName("算法06")
public class Hello06 {

    /**
     * leetcode-394 字符串解码
     * <p>
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * <p>
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * <p>
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * <p>
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     */
    @Test
    @DisplayName("字符串解码")
    public void test01() {
        //输入：s = "3[a]2[bc]" 输出："aaabcbc"
        //输入：s = "3[a2[c]]" 输出："accaccacc"
        //输入：s = "2[abc]3[cd]ef" 输出："abcabccdcdcdef"
        //输入：s = "abc3[cd]xyz" 输出："abccdcdcdxyz"
        Assertions.assertEquals(this.decodeString("3[a]2[bc]"), "aaabcbc");
        Assertions.assertEquals(this.decodeString("3[a2[c]]"), "accaccacc");
        Assertions.assertEquals(this.decodeString("2[abc]3[cd]ef"), "abcabccdcdcdef");
        Assertions.assertEquals(this.decodeString("abc3[cd]xyz"), "abccdcdcdxyz");

    }

    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        int ptr = 0;

        //入栈、出栈
        while(ptr < s.length()){
            char ch = s.charAt(ptr);

            if(ch == ']'){ //右中括号
                //出栈
                StringBuilder sbStr = new StringBuilder();//字符串
                StringBuilder sbNum = new StringBuilder();//数字

                //弹出中括号中的内容
                while(true){
                    if(stack.isEmpty()) break;
                    Character val = stack.peek();
                    if(val == '[') {
                        stack.pop();
                        break;
                    }
                    sbStr.append(stack.pop());
                }
                //弹出中括号前的数字
                while(true){
                    if(stack.isEmpty()) break;
                    Character val = stack.peek();
                    if(val >= '0' && val <= '9'){
                        sbNum.append(stack.pop());
                    }else{
                        break;
                    }
                }
                sbStr.reverse();
                sbNum.reverse();

                for(int i=0; i< Integer.valueOf(sbNum.toString()); i++){
                    for(int j=0; j<sbStr.length(); j++){
                        stack.push(sbStr.charAt(j));
                    }
                }
            } else{
                //入栈
                stack.push(ch);
            }
            ptr ++;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }
}
