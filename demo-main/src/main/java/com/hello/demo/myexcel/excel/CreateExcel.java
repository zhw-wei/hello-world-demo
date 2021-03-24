package com.hello.demo.myexcel.excel;

import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CreateExcel {

    private List<String> headerList = new ArrayList<String>(){{
        add("AAA"); add("BBB"); add("CCC"); add("DDD"); add("EEE");
    }};

    private List<Demo> demoList = Arrays.asList(new Demo(), new Demo(), new Demo(), new Demo(), new Demo());

    private List<String> fieldList = new ArrayList<String>(){{
        add("aaa"); add("bbb"); add("ccc"); add("ddd"); add("eee");
    }};

    public HSSFWorkbook createExcel(){
        HSSFWorkbook workbook = new HSSFWorkbook();
        this.createExcel(workbook, "hello001");
        this.createExcel(workbook, "hello002");
        this.createExcel(workbook, "hello003");
        this.createExcel(workbook, "hello004");

        return workbook;
    }

    private void createExcel(HSSFWorkbook workbook, String sheetName){
        Integer rowIndex = 0;

        //创建一个excel页面
        Sheet sheet = workbook.createSheet(sheetName);

        //设置头(第一行)
        Row row_0 = sheet.createRow(rowIndex++);
        //设置头样式 todo

        //设置头内容
        Integer index_0 = 0;
        for (String s : headerList) {
            Cell cell = row_0.createCell(index_0++);
            cell.setCellValue(s);
            //设置内容样式 todo
        }

        //设置内容(第二行~第N行)
        for (Demo demo : demoList) {
            Row row = sheet.createRow(rowIndex++);
            Integer index = 0;
            for (String s : fieldList) {

                Object obj = null;
                try {
                    PropertyDescriptor p = new PropertyDescriptor(s, demo.getClass());
                    Method method = p.getReadMethod();
                    obj = method.invoke(demo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Cell cell = row.createCell(index++);
                cell.setCellValue(Objects.nonNull(obj) ? obj.toString() : "");
            }
        }
    }

    @Data
    class Demo{
        private String aaa = "Aaa";
        private String bbb = "Bbb";
        private String ccc = "Ccc";
        private String ddd = "Ddd";
        private String eee = "Eee";
    }
}
