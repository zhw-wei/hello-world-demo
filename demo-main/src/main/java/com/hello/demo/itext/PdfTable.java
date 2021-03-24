package com.hello.demo.itext;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PdfTable {
    String headName() default "Head";
}
