package com.hello.demo.myexcel.excelv2;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelInfo {
    String headerName() default "HEAD";
    String defaultValue() default "";
    int width() default 5000;
    String parentVal() default "";
    int parentWidth() default 0;
    String sParentVal() default "";
    int sParentWidth() default 0;
}
