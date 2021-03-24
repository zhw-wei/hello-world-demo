package com.hello.demo.myexcel.excelv2;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateExcel {
    private List list;
    private List<String> display;
    private String sheetName;
    private CellStyle headCellStyle;
    private CellStyle contentCellStyle;

    public CreateExcel(List list, List<String> display, String sheetName){
        this.list = list;
        this.display = display;
        this.sheetName = sheetName;
    }

    public HSSFWorkbook createExcel(){
        HSSFWorkbook workbook = new HSSFWorkbook();

        this.initStyle(workbook);
        Sheet sheet = workbook.createSheet(sheetName);

        if(Objects.isNull(list) || list.isEmpty()) return workbook;
        if((this.list = (List) list.stream().filter(Objects::nonNull).collect(Collectors.toList())).isEmpty())
            return workbook;
        List<Field> fieldList = Stream.of(list.get(0).getClass().getDeclaredFields())
                .filter(var0 -> Objects.isNull(display) || display.isEmpty() || display.contains(var0.getName()))
                .filter(var1 -> Objects.nonNull(var1.getAnnotation(AssetExcel.class)))
                .collect(Collectors.toList());

        this.head(sheet, fieldList);
        this.content(sheet, 1, fieldList);

        return workbook;
    }

    private void head(Sheet sheet, List<Field> fieldList){
        Row row = sheet.createRow(0);
        Integer index = 0;
        for (Field field : fieldList) {
            AssetExcel assetExcel = field.getAnnotation(AssetExcel.class);

            Cell cell = row.createCell(index);
            cell.setCellValue(assetExcel.headerName());
            cell.setCellStyle(headCellStyle);
            sheet.setColumnWidth(index++, assetExcel.width());
        }
    }

    private void content(Sheet sheet, Integer rowIndex, List<Field> fieldList){
        for (Object var0 : list) {
            Row row = sheet.createRow(rowIndex++);
            Integer index = 0;
            for (Field field : fieldList) {
                AssetExcel assetExcel = field.getAnnotation(AssetExcel.class);
                Object obj = null;

                try {
                    PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), var0.getClass());
                    Method method = descriptor.getReadMethod();
                    obj = method.invoke(var0);
                    obj = Objects.nonNull(obj) ? obj.toString() : assetExcel.defaultValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Cell cell = row.createCell(index++);
                cell.setCellStyle(contentCellStyle);
                cell.setCellValue((String) obj);
            }
        }
    }

    private void initStyle(HSSFWorkbook workbook){
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 15);
        headCellStyle = workbook.createCellStyle();
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headCellStyle.setFont(headerFont);

        contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentCellStyle.setAlignment(HorizontalAlignment.CENTER);
    }
}
