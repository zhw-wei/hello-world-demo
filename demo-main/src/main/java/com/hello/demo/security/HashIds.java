package com.hello.demo.security;

import org.hashids.Hashids;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HashIds {

    static Integer maxLength = 6;
    static Map<String, Integer> map = new HashMap<>();

    /**
     * 生成邀请码
     */
    public static void main(String[] args) {

        int total = 100;
        long time = 0L;
        for (int i = 0; i < total; i++) {
            long start = System.currentTimeMillis();
            String result = test(i + 100000);
            long end = System.currentTimeMillis();

            Integer count = map.getOrDefault(result, 0);
            map.put(result, ++count);

            time += (end - start);
        }

        System.out.println(map.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey()).collect(Collectors.joining(", ")));

        System.out.println("spend time: " + time);
    }

    private static String test(Integer i) {

        Supplier<String> supplier = () -> UUID.randomUUID().toString() + UUID.randomUUID().toString();
        Hashids hashIds = new Hashids(supplier.get(), maxLength);

        String result = hashIds.encode(i);
        System.out.println(result);
        return result;
    }
}
