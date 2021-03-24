package com.hello.demo.itext;

import lombok.Data;

@Data
public class PdfObjectThree {

    @PdfTable(headName = "val0")
    private String val0 = "val0";

    @PdfTable(headName = "val1")
    private String val1 = "val1";

    @PdfTable(headName = "val2")
    private String val2 = "val2";

    @PdfTable(headName = "val3")
    private String val3 = "val3";

    @PdfTable(headName = "val4")
    private String val4 = "val4";

    @PdfTable(headName = "val5")
    private String val5 = "val5";
}
