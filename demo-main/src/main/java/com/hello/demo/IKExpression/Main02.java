package com.hello.demo.IKExpression;

import org.wltea.expression.ExpressionEvaluator;

public class Main02 {

    /**
     * 仅执行高级表达式
     * 字符串处理，将高级表达式独立出来
     * 处理方式： 下标读取，字符串切割
     */
    public static String analysisString(String expr) {
        for (; ; ) {
            final int firstIndex = expr.indexOf("$");
            if (firstIndex == -1) return expr;

            final int first = expr.substring(firstIndex).indexOf("(") + firstIndex;
            int stack = -1;
            int last = 0;
            for (int i = first + 1; i < expr.length(); i++) {
                if (expr.charAt(i) == ')') stack++;
                if (expr.charAt(i) == '(') stack--;
                if (stack == 0) {
                    last = i + 1;
                    break;
                }
            }
            String func = expr.substring(firstIndex, last);
            System.out.println(func);

            String result = String.valueOf(ExpressionEvaluator.evaluate(func));

            expr = expr.substring(0, firstIndex) + String.format("\"%s\"", result) + expr.substring(last);
            System.out.println(expr);
        }
    }

    public static String analysisString1(String expr){
        expr = analysisString(expr);
        expr = String.valueOf(ExpressionEvaluator.evaluate(expr));
        return expr;
    }
}
