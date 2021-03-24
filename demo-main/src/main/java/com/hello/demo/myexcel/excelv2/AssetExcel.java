package com.hello.demo.myexcel.excelv2;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AssetExcel {
    String headerName() default "HEAD";
    String defaultValue() default "";
    int width() default 5000;
}
