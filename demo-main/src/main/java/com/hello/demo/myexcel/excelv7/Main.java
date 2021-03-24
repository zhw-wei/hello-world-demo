package com.hello.demo.myexcel.excelv7;

import com.hello.demo.util.Common;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

        long time0 = System.currentTimeMillis();

        CreateExcel createExcel = new CreateExcel(TestObjectV7.class);

        List<TestObjectV7> list = new ArrayList<>();
        IntStream.range(0, 20).forEach(i -> list.add(new TestObjectV7()));
        HSSFWorkbook workbook = createExcel.createExcel(ExcelContents.a1, list);
        File file = Common.filePathExcel();
        workbook.write(file);

        long time1 = System.currentTimeMillis();
        System.out.println(time1 - time0);
    }
}
