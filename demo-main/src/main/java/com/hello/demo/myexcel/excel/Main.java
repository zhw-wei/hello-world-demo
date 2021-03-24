package com.hello.demo.myexcel.excel;

import com.hello.demo.util.SecurityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final String filePath_t = "D:/test/demo/%s.xls";

    public static void main(String[] args) throws IOException {
        CreateExcel createExcel = new CreateExcel();
        HSSFWorkbook workbook = createExcel.createExcel();

        String filePath = String.format(filePath_t, SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        workbook.write(file);
    }
}
