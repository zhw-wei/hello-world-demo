package com.hello.demo.myexcel.excelV4;

import com.hello.demo.util.SecurityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class Main {
    private static final String filePath_t = "D:/test/demo/%s.xls";
    private static final String filePath_m = "/Users/zhw/test/%s.xls";

    private static final Boolean MAC = System.getProperties().get("os.name").toString().toLowerCase().contains("mac");

    public static void main(String[] args) throws IOException {

        ExcelTempCreate<TestObject> excelTempCreate = new ExcelTempCreate<>(TestObject.class, "hello world");

        HSSFWorkbook workbook = excelTempCreate.createTemplate();

        String filePath = String.format(MAC ? filePath_m : filePath_t, SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        workbook.write(file);
    }
}
