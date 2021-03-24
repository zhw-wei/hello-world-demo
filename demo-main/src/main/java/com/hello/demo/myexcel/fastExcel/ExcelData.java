package com.hello.demo.myexcel.fastExcel;

import lombok.Data;

import java.util.List;

/**
 * excel中的数据
 * @param <T>
 */
@Data
public class ExcelData<T> {
    private String sheetName;
    private List<T> excelData;
}
