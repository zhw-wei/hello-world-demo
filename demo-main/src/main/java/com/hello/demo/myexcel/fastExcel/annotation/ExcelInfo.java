package com.hello.demo.myexcel.fastExcel.annotation;

import java.lang.annotation.*;

/**
 * 描述生成excel的对象
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelInfo {
    /**
     * 是否展示excel头部
     */
    boolean displayHeader() default true;
}
