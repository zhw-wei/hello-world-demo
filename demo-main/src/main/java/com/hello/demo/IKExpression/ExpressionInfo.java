package com.hello.demo.IKExpression;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * 自定义函数
 */
public class ExpressionInfo {

    private static String dateFormat = "yyyy-MM-dd";
    private static String dateFormatSs = "yyyy-MM-dd HH:mm:ss";

    private static Function<String, ZonedDateTime> str2Date = dateStr -> {
        if (dateStr.length() <= dateFormat.length()) dateStr += " 00:00:00";
        ZonedDateTime time = ZonedDateTime.parse(dateStr,
                DateTimeFormatter.ofPattern(dateFormatSs).withZone(ZoneId.systemDefault()));
        return time;
    };

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String now() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 获取年
     */
    public static Integer year(String dateStr) {
        return str2Date.apply(dateStr).getYear();
    }

    /**
     * 获取季
     */
    public static Integer quarter(String dateStr) {
        ZonedDateTime time = str2Date.apply(dateStr);
        int month = time.getMonthValue();

        if (month <= 3) return 1;
        if (month <= 6) return 2;
        if (month <= 9) return 3;
        return 4;
    }

    /**
     * 获取月
     */
    public static Integer month(String dateStr) {
        ZonedDateTime time = str2Date.apply(dateStr);
        return time.getMonthValue();
    }

    /**
     * 获取日
     */
    public static Integer day(String dateStr) {
        return str2Date.apply(dateStr).getDayOfMonth();
    }

    /**
     * 比较日期大小
     *
     * @param unit 时间单位, 'Y'年、'M'月、'D'日
     */
    public static Integer dateDif(String date0, String date1, String unit) {
        ZonedDateTime time0 = str2Date.apply(date0);
        ZonedDateTime time1 = str2Date.apply(date1);

        Integer dif = 0;
        switch (unit) {
            case "D": {
                //日期之间的天数差，DATEDIF('2019-10-10', '2019-10-11', 'D') 刚好差1天，返回1
                //时间之间的天数差，DATEDIF('2019-10-10 16:00:00', '2019-10-11 15:59:59', 'D') 差额不足1天，返回0
                long t0 = time0.toInstant().getEpochSecond();
                long t1 = time1.toInstant().getEpochSecond();
                dif = Math.toIntExact((t0 - t1) / (24 * 60 * 60));
                break;
            }
            case "M": {
                // 日期之间的月份差，DATEDIF('2020-10-10', '2019-11-09', 'M') 差额不足1月，返回0
                if (time0.getDayOfMonth() < time1.getDayOfMonth()) time1 = time1.plusMonths(1L);
                dif = (time0.getYear() - time1.getYear()) * 12 + time0.getMonthValue() - time1.getMonthValue();
                break;
            }
            case "Y": {
                //日期之间的年份差，DATEDIF('2019-10-10', '2020-10-09', 'Y') 差额不足1年，返回0；
                if (time0.getDayOfMonth() < time1.getDayOfMonth()) time1 = time1.plusYears(1L);
                dif = time0.getYear() - time1.getYear();
                break;
            }
        }

        return dif;
    }

    /**
     * 日期增减
     */
    public static String dateAdd(String datePart, Integer number, String date) {
        ZonedDateTime time = str2Date.apply(date);
        String dateFormat = ExpressionInfo.dateFormat;
        switch (datePart) {
            case "Hour": {
                time = time.plusHours(number);
                dateFormat = ExpressionInfo.dateFormatSs;
                break;
            }
            case "Day": {
                time = time.plusDays(number);
                break;
            }
        }
        return time.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 字符串转日期
     */
    public static String date(String date) {
        date = date.split(" ")[0];
        if (date.contains("/")) date = date.replaceAll("/", "-");
        return date;
    }

    /**
     * 转换日期格式
     */
    public static String date2Str(Integer year, Integer month, Integer day) {
        ZonedDateTime time = ZonedDateTime.of(year, month, day,
                0, 0, 0, 0, ZoneId.systemDefault());
        return time.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 绝对值
     */
    public static String abs(Double str){
        BigDecimal decimal = new BigDecimal(str);
        decimal = decimal.abs();
        return decimal.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 对小数点后的数四舍五入
     */
    public static String round(Double str, Integer scale){
        BigDecimal decimal = new BigDecimal(str).setScale(scale, RoundingMode.HALF_EVEN);
        return decimal.toPlainString();
    }
}
