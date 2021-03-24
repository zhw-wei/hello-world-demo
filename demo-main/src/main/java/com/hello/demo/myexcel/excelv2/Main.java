package com.hello.demo.myexcel.excelv2;

import com.hello.demo.util.SecurityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String filePath_t = "D:/test/demo/%s.xls";
    private static final String filePath_m = "/Users/zhw/test/%s.xls";

    private static final Boolean MAC = System.getProperties().get("os.name").toString().toLowerCase().contains("mac");

    public static void main(String[] args) throws IOException {

        List<StoreGoodsRelDetailPageResDto> list = Arrays.asList(new StoreGoodsRelDetailPageResDto(),
                new StoreGoodsRelDetailPageResDto(),
                new StoreGoodsRelDetailPageResDto(),
                new StoreGoodsRelDetailPageResDto());

//        CreateExcelV2 createExcel = new CreateExcelV2(list, "HELLO", Arrays.asList("hello world", "hello world 2"));
        CreateExcelV2 createExcel = new CreateExcelV2(list, "HELLO", true);
        createExcel.setBaseParent("Hello world!");

        HSSFWorkbook workbook = createExcel.createExcel();

        String filePath = String.format(MAC? filePath_m : filePath_t, SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        workbook.write(file);
    }
}
