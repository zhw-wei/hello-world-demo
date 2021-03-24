package com.hello.demo.myexcel.fastExcel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface Excel {
    HSSFWorkbook createExcel() throws Exception;
}
