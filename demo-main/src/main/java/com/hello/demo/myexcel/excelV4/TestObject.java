package com.hello.demo.myexcel.excelV4;

import lombok.Data;

@Data
public class TestObject {
    @ExcelTempInfo(headerName = "TEST01", color = ExcelColor.BLACK, comboBox = {"aa", "bb", "cc"})
    private String test01;

    @ExcelTempInfo(headerName = "TEST02", color = ExcelColor.BLUE, comboBox = {"dd", "ee", "ff", "gg"})
    private String test02;

    @ExcelTempInfo(headerName = "*TEST03(必填)", color = ExcelColor.RED, comboBox = {"是", "否"})
    private String test03;

    @ExcelTempInfo(headerName = "TEST04", color = ExcelColor.YELLOW, parentVal = "TEST_PARENT", parentWidth = 3, parentColor = ExcelColor.RED)
    private String test04;

    @ExcelTempInfo(headerName = "*TEST05(必填)", color = ExcelColor.RED)
    private String test05;

    @ExcelTempInfo(headerName = "TEST06", color = ExcelColor.BLACK)
    private String test06;
}
