package com.hello.demo.util;

import java.util.HashMap;
import java.util.Map;

public class LangExchange {
    static Map<Integer, String> map = new HashMap<Integer, String>() {{
        put(1, "一");
        put(2, "二");
        put(3, "三");
        put(4, "四");
        put(5, "五");
        put(6, "六");
        put(7, "七");
        put(8, "八");
        put(9, "九");
        put(0, "零");
    }};

    public static String floor(Integer floor) {
        StringBuilder res = new StringBuilder();
        if (floor < 0) res.append("负");

        floor = Math.abs(floor);

        if(floor == 0) res.append(map.get(floor));
        if (floor >= 10 && floor < 20) {
            res.append("十");
            floor = floor % 10;
        }

        if (floor >= 1000) {
            res.append(map.get(floor / 1000) + "千");
            floor = floor % 1000;
            if (floor < 100 && floor != 0) res.append("零");
        }

        if (floor >= 100) {
            res.append(map.get(floor / 100) + "百");
            floor = floor % 100;
            if (floor < 10 && floor != 0) res.append("零");
        }

        if (floor >= 10) {
            res.append(map.get(floor / 10) + "十");
            floor = floor % 10;
        }

        if (floor > 0) res.append(map.get(floor));

        return res.toString();
    }
}
