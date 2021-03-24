package com.hello.demo.myexcel.excelv6;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelFormOne {
    String headName() default "Head";
    boolean summary() default false;
}
