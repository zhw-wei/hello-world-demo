package com.hello.demo.util;

import lombok.Data;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FunctionUtils {

    Predicate<String> check_string = str -> Objects.nonNull(str) && !str.trim().equals("");

    Function<String, Date> string_to_date = str ->
            Objects.nonNull(str) && !str.trim().equals("") ? new Date(ZonedDateTime.parse(str + "T00:00:00Z").toInstant().getEpochSecond() * 1000) : null;

    Function<BigDecimal, String> decimal_to_string_4 = var0 -> Objects.isNull(var0) ? "0.0000" : new DecimalFormat("0.0000").format(var0);

    Function<BigDecimal, String> decimal_to_string_2 = var0 -> Objects.isNull(var0) ? "0.00" : new DecimalFormat("0.00").format(var0);

    Function<ZonedDateTime, Date> zoned_to_date = zoned -> new Date(zoned.toInstant().getEpochSecond() * 1000);

    Function<String, ZonedDateTime> string_to_zoned = str -> ZonedDateTime.parse(str + "T00:00:00Z");

    BiFunction<String, String, TimeInfo> time_info = (startTime, endTime) -> {

        //当前
        ZonedDateTime currentEndTime = string_to_zoned.apply(endTime);
        ZonedDateTime currentStartTime = string_to_zoned.apply(startTime);

        //日期间相差的天数
        long day = (currentEndTime.toInstant().getEpochSecond() - currentStartTime.toInstant().getEpochSecond())/(24*60*60);

        //同比
        ZonedDateTime yearEndTime = currentEndTime.plusYears(-1L);
        ZonedDateTime yearStartTime = currentStartTime.plusYears(-1L);

        //环比
        ZonedDateTime seqEndTime = currentStartTime.plusDays(-1);
        ZonedDateTime seqStartTime = seqEndTime.plusDays(day*-1);

        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setCurrentEndTime(zoned_to_date.apply(currentEndTime));
        timeInfo.setCurrentStartTime(zoned_to_date.apply(currentStartTime));
        timeInfo.setYearEndTime(zoned_to_date.apply(yearEndTime));
        timeInfo.setYearStartTime(zoned_to_date.apply(yearStartTime));
        timeInfo.setSeqEndTime(zoned_to_date.apply(seqEndTime));
        timeInfo.setSeqStartTime(zoned_to_date.apply(seqStartTime));
        return timeInfo;
    };

    /**
     * 根据开始时间计算出对应的同比/环比时间
     */
    @Data
    class TimeInfo {
        //当前
        private Date currentStartTime;
        private Date currentEndTime;

        //同比 -- 减少一年
        private Date yearStartTime;
        private Date yearEndTime;

        //环比 -- 减少 (start - end)
        private Date seqStartTime;
        private Date seqEndTime;
    }
}
