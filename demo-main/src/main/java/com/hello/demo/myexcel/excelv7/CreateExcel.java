package com.hello.demo.myexcel.excelv7;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CreateExcel<T> {

    private Class<T> classInfo;
    private CellStyle headStyle;
    private CellStyle bodyStyle;
    private CellStyle redStyle;

    public CreateExcel(Class<T> classInfo) {
        this.classInfo = classInfo;
    }

    public HSSFWorkbook createExcel(String flag, List<T> excelList) {

        List<Field> fieldList = Arrays.asList(classInfo.getDeclaredFields()).stream()
                .filter(var0 -> Objects.nonNull(var0.getDeclaredAnnotation(ExcelInfo.class)))
                .filter(var1 -> Arrays.asList(var1.getDeclaredAnnotation(ExcelInfo.class).flag()).contains(flag))
                .sorted(Comparator.comparingInt(var2 -> var2.getDeclaredAnnotation(ExcelInfo.class).sort()))
                .collect(Collectors.toList());

        HSSFWorkbook workbook = new HSSFWorkbook();
        headStyle = this.headStyle(workbook);
        bodyStyle = this.bodyStyle(workbook);
        redStyle = this.redStyle(workbook);
        Sheet sheet = workbook.createSheet();

        int index = 0;
        index = this.head(sheet, fieldList, index);
        index = this.body(sheet, fieldList, excelList, index);

        return workbook;
    }

    private Integer head(Sheet sheet, List<Field> fieldList, int index) {

        Row row = sheet.createRow(index++);

        int cellIndex = 0;
        for (Field field : fieldList) {
            ExcelInfo excelInfo = field.getDeclaredAnnotation(ExcelInfo.class);
            Cell cell = row.createCell(cellIndex);
            cell.setCellValue(excelInfo.headName());
            cell.setCellStyle(this.headStyle);
            sheet.setColumnWidth(cellIndex, excelInfo.width());
            cellIndex++;
        }

        return index;
    }

    private Integer body(Sheet sheet, List<Field> fieldList, List<T> excelList, int index) {

        for (int i = 0; i < excelList.size(); i++) {
            Row row = sheet.createRow(index++);
            int cellIndex = 0;
            for (Field field : fieldList) {
                Cell cell = row.createCell(cellIndex++);
                cell.setCellValue(readValue.apply(excelList.get(i), field.getName()));
                //最后两行设为红色字体
                cell.setCellStyle(i > excelList.size() - 3 ? redStyle : bodyStyle);
            }
        }
        return index;
    }

    //缓存反射方法
    Map<String, Method> readMethodMap = new HashMap<>();
    //反射获取值
    BiFunction<T, String, String> readValue = (T obj, String str) -> {
        String result = String.valueOf(Double.valueOf(Math.random() * 100).intValue());
        try {
            Method method = readMethodMap.getOrDefault(str, new PropertyDescriptor(str, classInfo).getReadMethod());
            readMethodMap.putIfAbsent(str, method);
            Object value = method.invoke(obj);
            result = Objects.nonNull(value) ? value.toString() : result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    };

    private CellStyle headStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 13);
        style.setFont(font);

        return style;
    }

    private CellStyle bodyStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);

        return style;
    }

    private CellStyle redStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setColor(Font.COLOR_RED);
        style.setFont(font);

        return style;
    }
}
