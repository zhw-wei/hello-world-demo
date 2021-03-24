package com.hello.demo.myexcel.fastExcel;

import com.hello.demo.myexcel.fastExcel.annotation.ExcelInfo;
import com.hello.demo.myexcel.fastExcel.annotation.FieldDescription;
import com.hello.demo.myexcel.fastExcel.core.ExcelCreate;
import com.hello.demo.myexcel.fastExcel.core.ExcelExportConfig;
import com.hello.demo.util.HelloDTO;
import com.hello.demo.util.SecurityUtils;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Main {
    private static final String filePath_t = "D:/test/demo/%s.xls";
    public static void main(String[] args) throws Exception {
        Excel excel = new ExcelCreate(new ExcelExportConfig(), Arrays.asList(excelData(), excelData(), excelData(), excelData2()));
        HSSFWorkbook workbook = excel.createExcel();

        String filePath = String.format(filePath_t, SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        workbook.write(file);
    }

    @Data
    @ExcelInfo()
    public static class Demo{
        @FieldDescription(headerName = "hello", defaultValue = "hello", width = 2000)
        private String hello = "word";

        @FieldDescription(headerName = "hello1", defaultValue = "hello", width = 5000)
        private String hello1;
        @FieldDescription(headerName = "hello1", defaultValue = "hello", width = 5000)
        private Date hello2 = new Date();
    }

    private static ExcelData excelData(){
        ExcelData excelData = new ExcelData();
        excelData.setSheetName("hello-world" + new Random().nextInt(100));
        excelData.setExcelData(Arrays.asList(new Demo(), new Demo(), new Demo()));
        return excelData;
    }
    private static ExcelData excelData2(){
        ExcelData excelData = new ExcelData();
        excelData.setSheetName("hello-world" + new Random().nextInt(100));
        excelData.setExcelData(Arrays.asList(new HelloDTO(), new HelloDTO(), new HelloDTO()));
        return excelData;
    }
}
