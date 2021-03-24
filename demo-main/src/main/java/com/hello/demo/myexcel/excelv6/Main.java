package com.hello.demo.myexcel.excelv6;

import com.hello.demo.util.SecurityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final String filePath_t = "D:/test/demo/%s.xls";
    private static final String filePath_m = "/Users/zhw/test/%s.xls";

    private static final Boolean MAC = System.getProperties().get("os.name").toString().toLowerCase().contains("mac");

    public static void main(String[] args) throws IOException {
        CreateExcelForm create = new CreateExcelForm("测试表单导出",
                new ObjectA(), Arrays.asList(new ObjectB(), new ObjectB(), new ObjectB(), new ObjectB(), new ObjectB()),
                Arrays.asList(new ObjectC(), new ObjectC(), new ObjectC(), new ObjectC(), new ObjectC(), new ObjectC()),
                true, true);

        HSSFWorkbook workbook = create.createExcel();

        String filePath = String.format(MAC ? filePath_m : filePath_t, SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        workbook.write(file);
    }
}
