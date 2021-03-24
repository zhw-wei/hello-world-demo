package com.hello.demo;

import com.hello.demo.util.FunctionUtils;
import com.hello.demo.util.SimpleBeanCopyUtil;
import com.hello.demo.util.dto.ObjA;
import com.hello.demo.util.dto.ObjB;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> instIdList = new ArrayList<String>(){{
            add("1,2,3");
            add("4,5,6");
            add("7,8,9");
        }};
        List<Integer> equipmentInstIdList = instIdList.stream().flatMap(var0 -> Stream.of(var0.split(","))).map(var1 -> Integer.valueOf(var1))
                .collect(Collectors.toList());
        System.out.println(equipmentInstIdList);
    }
}
