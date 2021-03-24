package com.hello.demo.myexcel.excelv6;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelFormTwo {
    String headName() default "Head";
    int order() default 0;
}
