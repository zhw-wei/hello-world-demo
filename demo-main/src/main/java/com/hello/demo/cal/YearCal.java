package com.hello.demo.cal;

import java.util.HashMap;
import java.util.Map;

public class YearCal {

    private static Map<Integer, String> map = new HashMap<Integer, String>(){{
        put(1, "一月");put(2, "二月");put(3, "三月");put(4, "四月");
        put(5, "五月");put(6, "六月");put(7, "七月");put(8, "八月");
        put(9, "九月");put(10, "十月");put(11, "十一月");put(12, "十二月");
    }};

    public static void yearCal(){
        for(int i=1; i<=12; i++){
            System.out.println("\t\t\t\t\t\t" + map.get(i));
            MonthCal.month(i);
        }
    }
}
