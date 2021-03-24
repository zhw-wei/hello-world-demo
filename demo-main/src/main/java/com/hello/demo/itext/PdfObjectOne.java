package com.hello.demo.itext;

import lombok.Data;

@Data
public class PdfObjectOne {

    @PdfForm(headName = "val0")
    private String val0 = "val0";

    @PdfForm(headName = "val1")
    private String val1 = "val1";

    @PdfForm(headName = "val2")
    private String val2 = "val2";

    @PdfForm(headName = "val3")
    private String val3 = "val3";

    @PdfForm(headName = "val4")
    private String val4 = "val4";
}
