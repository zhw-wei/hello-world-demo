package com.hello.demo.myexcel.excelv7;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelInfo {
    String headName();
    String[] flag();
    int sort();
    int width() default 5000;
}
