package com.hello.demo.myexcel.excelv3;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExplainExcelInfo {
    int line() default 0;   //列数从0开始
}
