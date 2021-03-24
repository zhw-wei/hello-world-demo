package com.hello.demo.myexcel.fastExcel.annotation;

import java.lang.annotation.*;

/**
 * excel列描述
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldDescription {
    int minHeight = 250, minWidth = 2500, minHeadHeight = 300;
    /**
     * 列名称，即该第一行内容
     */
    String headerName() default "HEAD";

    /**
     * 值为null时的默认值
     */
    String defaultValue() default "";

    /**
     * 日期格式，默认格式yyyy-MM-dd HH:mm:ss
     */
    String dateFormat() default "yyyy-MM-dd HH:mm:ss";

    /**
     * 宽度
     */
     int width() default minWidth;

    /**
     * 高度
     */
    int height() default minHeight;

    /**
     * 每列的第一行高度
     */
    int headHeight() default minHeadHeight;

    /**
     * 第一行的字体大小
     */
    int headFontSize() default 300;

    /**
     * 字体大小
     */
    int fontSize() default 250;
}
