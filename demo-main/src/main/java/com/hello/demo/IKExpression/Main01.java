package com.hello.demo.IKExpression;

import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main01 {

    private static Consumer<String> consumer = str ->
            System.out.println("result = " + ExpressionEvaluator.evaluate(str));

    public static void test01() {
        //定义表达式
        String expression = "\"Hello World \" + 用户名";
        //给表达式中的变量 "用户名" 付上下文的值
        List<Variable> variables = new ArrayList<>();
        variables.add(Variable.createVariable("用户名", "hello user"));
        //执行表达式
        Object result = ExpressionEvaluator.evaluate(expression, variables);
        System.out.println("Result = " + result);
    }

    public static void test02() {
        consumer.accept("$NOW()");
    }

    public static void test03() {
        consumer.accept("$YEAR(\"2020-09-09\")");
    }

    public static void test04() {
        consumer.accept("$YEAR($NOW())");
    }

    public static void test05() {
        consumer.accept("$QUARTER($NOW())");
    }

    public static void test06() {
        consumer.accept("$MONTH($NOW())");
    }

    public static void test07() {
        consumer.accept("$MONTH($NOW()) - 1");
    }

    public static void test08() {
        consumer.accept("\"2020-10-27\" == $NOW() + abc");
    }

    public static void test09() {
        consumer.accept("$DATEDIF(\"2020-11-05\",\"2020-11-06 10:10:10\", \"D\")");
    }

    public static void test10() {
        consumer.accept("$DATEDIF(\"2020-11-05\",\"2020-10-05\", \"M\")");
    }

    public static void test11() {
        consumer.accept("$DATEDIF(\"2020-11-05\",\"2019-11-06\", \"Y\")");
    }

    public static void test12() {
        consumer.accept("$DATEADD(\"Hour\", 2, \"2019-11-06\")");
    }

    public static void test13() {
        consumer.accept("$DATEADD(\"Day\", 2, \"2019-11-06\")");
    }

    public static void test14() {
        consumer.accept("$DATE(\"2019-11-06\")");
    }

    public static void test15() {
        consumer.accept("$DATE(\"2019/11/06\")");
    }

    public static void test16() {
        consumer.accept("$DATE(\"2019/11/06 00:00:00\")");
    }

    public static void test17() {
        consumer.accept("$DATESTR(2020, 1, 2)");
    }

    public static void test18() {
        consumer.accept("$ABS(-123.440001)");
        consumer.accept("$ABS(-123)");
        consumer.accept("$ABS(-123/10)");
    }

    public static void test19() {
        consumer.accept("$ROUND(-123.4422, 2)");
    }

    public static void test20() {
        consumer.accept("$ROUND(\"-123.4455\", 2)");
        consumer.accept("$ROUND(-123, 2)");
        consumer.accept("$ROUND(-123/(10+1), 2)");
    }

    public static void test21() {
        consumer.accept("\"张三\" + \"张三\" + $DATEADD(\"Hour\", -1, $NOW())");
    }

    public static void test22() {
        consumer.accept("1==1");
    }

    public static void test23() {
//        consumer.accept("1=1");//error
    }

    public static void test24() {
        consumer.accept("1 > 1 && 3 > 4");
    }

    public static void test25() {
        Main02.analysisString("$DAY(\"2020-11-30 11:12:13\") == \"2020-11-30\"");
        System.out.println("========");
        System.out.println(Main02.analysisString1("$DAY(\"2020-11-30 14:15:16\") == \"2020-11-30\""));
    }
}
