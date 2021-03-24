package com.hello.demo.myexcel.excel;

import com.hello.demo.util.SecurityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main_2 {
    private static final String filePath_t = "D:/test/demo/%s.xls";
    public static void main(String[] args) throws IOException {
        AssetHouseCardExcel excel = new AssetHouseCardExcel();

        HSSFWorkbook workbook = excel.createCardExcel(Arrays.asList(new AssetHouse(), new AssetHouse(), new AssetHouse()));

        String filePath = String.format(filePath_t, SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        workbook.write(file);
    }
}
