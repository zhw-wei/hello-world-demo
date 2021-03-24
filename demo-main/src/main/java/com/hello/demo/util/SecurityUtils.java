package com.hello.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class SecurityUtils {
    private static String[] code = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z",
            "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "$", "%", "&", "+", "=", "#", "\\n"};

    public static String security(Integer length){
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++)
            sb.append(code[random.nextInt(code.length)]);

        return sb.toString();
    }

    public static String verifyCode(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<6; i++) {
            if (random.nextBoolean()) {
                sb.append((char) (random.nextInt(25) + 97));
            } else {
                sb.append(random.nextInt(10));
            }
        }

        return sb.toString();
    }

    public static String createNickName(){
        Random random = new Random();
        String[] uuidArr = UUID.randomUUID().toString().split("-");
        return "Way_" + (random.nextBoolean()? uuidArr[0] + uuidArr[1+random.nextInt(3)] : uuidArr[4]);
    }

    //生成7位验证码，第一位不能是0
    public static String random(){
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        list.add(random.ints(3, 1, 9).findAny().getAsInt());
        random.ints(6, 0, 9).forEach(var0 -> list.add(var0));

        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
