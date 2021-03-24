package com.hello.demo.myexcel.excelv6;

import lombok.Data;

@Data
public class ObjectB {
    @ExcelFormTwo(headName = "val0")
    private String val0 = "val0";

    @ExcelFormTwo(headName = "val1")
    private String val1 = "val1";

    @ExcelFormTwo(headName = "val2")
    private String val2 = "val2";

    @ExcelFormTwo(headName = "val3")
    private String val3 = "val3";

    @ExcelFormTwo(headName = "val4")
    private String val4 = "val4";

    @ExcelFormTwo(headName = "val5")
    private String val5 = "val5";

    @ExcelFormTwo(headName = "val6")
    private String val6 = "val6";

    @ExcelFormTwo(headName = "val7", order = 2)
    private String val7 = "val7";

    @ExcelFormTwo(headName = "val8", order = 1)
    private String val8 = "val8";

    @ExcelFormTwo(headName = "val9")
    private String val9 = "val9";

    @ExcelFormTwo(headName = "val10")
    private String val10 = "val10";

    @ExcelFormTwo(headName = "val11")
    private String val11 = "val11";
}
