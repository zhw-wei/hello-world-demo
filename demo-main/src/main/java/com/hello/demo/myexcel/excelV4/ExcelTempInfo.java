package com.hello.demo.myexcel.excelV4;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTempInfo {
    String headerName() default "HEAD";

    int width() default 5000;

    String[] comboBox() default {};//下拉框

    ExcelColor color() default ExcelColor.BLACK;

    String parentVal() default "PARENT";//父级合并框内容

    int parentWidth() default 0;//父级合并框占用的列

    ExcelColor parentColor() default ExcelColor.BLACK;
}
