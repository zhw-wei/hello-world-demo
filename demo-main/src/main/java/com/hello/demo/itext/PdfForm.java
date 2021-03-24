package com.hello.demo.itext;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PdfForm {
    String headName() default "Head";
}
