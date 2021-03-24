package com.hello.demo.regular;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    /**
     * 正则表达式
     */
    public static void main(String[] args) {
        test03();
    }

    private static void test01() {
        String str0 = "^\\d+\\.?\\d*$";//判断是否是数字
        Pattern pattern = Pattern.compile(str0);
        Matcher matcher = pattern.matcher("1");
        System.out.println(matcher.matches());

        System.out.println(new BigDecimal("99."));
    }

    private static void test02() {
        String expr = "abc d=ef == == >= >= <= !=  ";
        System.out.println(expr);
        StringBuilder sb = new StringBuilder();

        int queue = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (queue == -1) {
                if (c != '=') sb.append('=');
                queue = 0;
            } else if (queue == 0 && c == '=') {
                queue = -1;
            }

            sb.append(expr.charAt(i));
        }
        expr = sb.toString();
        System.out.println(expr);
        expr = expr.replaceAll(">==", ">=");
        expr = expr.replaceAll("<==", "<=");
        expr = expr.replaceAll("!==", "!=");
        System.out.println(expr);
    }

    private static void test03(){
        Pattern pattern = Pattern.compile("\\{\\w+\\.?\\w+\\}");
        String str = "hello {goods_type_names} world {apply_user_name} " +
                "hello {apply_user_name.apply_user_name} hello {abc} hello ";
        List<String> list = new ArrayList<>();
        while (true) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String group = matcher.group();
                list.add(group);
                str = str.replace(group, " ");
            } else{
                break;
            }
        }
        System.out.println(list);
        System.out.println(str);
    }
}
