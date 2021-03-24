package com.hello.demo.util;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MybatisUtils {
    //aa_bb -- aaBb
    static Function<String, String> strFun = s -> {
        StringBuilder sb = new StringBuilder();
        String[] strArr = s.split("_");
        sb.append(strArr[0]);

        for (int i = 1; i < strArr.length; i++) {
            byte[] b = strArr[i].getBytes();
            b[0] -= 32;
            sb.append(new String(b));
        }
        return sb.toString();
    };

    //aa_bb -- #{aaBb}
    public static String insert(String strs) {
        String temp = "#{%s}";

        return Stream.of(strs.split(",")).map(s -> s.trim())
                .map(s -> String.format(temp, strFun.apply(s))).collect(Collectors.joining(", "));
    }

    //referral_code -- <if test="referralCode != null">referral_code = #{referralCode},</if>
    public static String update(String strs) {
        String temp = "<if test=\"%s != null\">%s = #{%s},</if>";

        return Stream.of(strs.split(",")).map(s -> s.trim())
                .map(s -> String.format(temp, strFun.apply(s), s, strFun.apply(s))).collect(Collectors.joining("\n"));
    }
    //referral_code -- <if test="referralCode != null">referral_code = #{referralCode},</if>
    public static String update2(String strs) {
        String temp = "\"<if test='%s != null'>%s = #{%s},</if>\",";

        return Stream.of(strs.split(",")).map(s -> s.trim())
                .map(s -> String.format(temp, strFun.apply(s), s, strFun.apply(s))).collect(Collectors.joining("\n"));
    }

    static {
        System.out.println("hello world");
    }
}
