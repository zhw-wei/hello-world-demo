package com.hello.demo.myexcel.fastExcel.core;

import com.hello.demo.myexcel.fastExcel.annotation.FieldDescription;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 * 对象转字符串
 */
class ToString {

    protected static String to(Object obj, FieldDescription fieldDesc){
        String format = fieldDesc.dateFormat();

        if(obj instanceof String) return (String) obj;
        if(obj instanceof Date) return to(((Date) obj).getTime(), format);
        if(obj instanceof Timestamp) return to(((Timestamp)obj).getTime(), format);

        return obj.toString();
    }

    private static String to(Long epochMilli, String format){
        ZonedDateTime time = ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    public static String toString(Object obj) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Function<Long, String> time2Str = var0 -> ZonedDateTime.ofInstant(Instant.ofEpochMilli(var0),
                ZoneId.systemDefault()).format(formatter);
        if (Objects.isNull(obj)) return "";
        if (obj instanceof String) return (String) obj;
        if (obj instanceof Date) return time2Str.apply(((Date) obj).getTime());
        if (obj instanceof Timestamp) return time2Str.apply(((Timestamp) obj).getTime());

        return obj.toString();
    }

    public static void main(String[] args) {
        System.out.println(toString(new Date()));
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }
}
