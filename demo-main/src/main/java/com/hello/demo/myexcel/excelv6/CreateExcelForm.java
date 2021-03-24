package com.hello.demo.myexcel.excelv6;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateExcelForm<A, B, C> {

    private String title;
    private A aObj;
    private List<B> bList;
    private List<C> cList;

    private Boolean showSummary;
    private Boolean showSign;

    private Integer width = 5000;
    private Integer maxFieldLength = 8;

    public CreateExcelForm(String title, A aObj, List<B> bList, List<C> cList,
                           Boolean showSummary, Boolean showSign) {
        this.title = title;
        this.aObj = aObj;
        this.bList = bList;
        this.cList = cList;
        this.showSummary = showSummary;
        this.showSign = showSign;
    }

    Function<List, Integer> lengthFun = list -> {
        if (Objects.nonNull(list) && list.size() > 0) {
            List<Field> fieldList = Stream.of(list.get(0).getClass().getDeclaredFields())
                    .filter(var0 -> Objects.nonNull(var0.getAnnotation(ExcelFormTwo.class)))
                    .collect(Collectors.toList());
            return fieldList.size();
        }
        return 0;
    };

    public HSSFWorkbook createExcel() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(title);

        maxFieldLength = Math.max(maxFieldLength, lengthFun.apply(bList) - 1);
        maxFieldLength = Math.max(maxFieldLength, lengthFun.apply(cList) - 1);
        //init style
        this.style(style_0, workbook);
        this.style(style_1, workbook);
        this.style(style_2, workbook);

        Integer rowIndex = 0;
        rowIndex = this.head(sheet, rowIndex);
        rowIndex = this.formOne(sheet, rowIndex);
        rowIndex = this.formTwo(sheet, rowIndex);
        rowIndex = this.formThree(sheet, rowIndex);
        rowIndex = this.sign(sheet, rowIndex);

        return workbook;
    }

    private Integer head(Sheet sheet, Integer rowIndex) {
        Row row = sheet.createRow(rowIndex);
        CellStyle style = styleMap.get(style_0);

        Cell cell = row.createCell(0);
        cell.setCellValue(title);
        cell.setCellStyle(style);
        row.setHeight((short) 500);

        CellRangeAddress range = new CellRangeAddress(rowIndex, rowIndex, 0, maxFieldLength);
        sheet.addMergedRegion(range);

        return ++rowIndex;
    }

    private Integer formOne(Sheet sheet, Integer rowIndex) {
        Class<A> classAInfo = (Class<A>) aObj.getClass();
        CellStyle contentStyle = styleMap.get(style_2);

        List<Field> baseList = Stream.of(classAInfo.getDeclaredFields())
                .filter(var0 -> Objects.nonNull(var0.getAnnotation(ExcelFormOne.class)))
                .collect(Collectors.toList());

        List<Field> fieldList = baseList.stream()
                .filter(var0 -> !var0.getAnnotation(ExcelFormOne.class).summary())
                .collect(Collectors.toList());

        SBiConsumer<Cell, Cell, Field> consumer = (cell_0, cell_1, field) -> {
            ExcelFormOne formOne = field.getAnnotation(ExcelFormOne.class);
            String value_0 = formOne.headName();

            Object val = readValue(field, classAInfo, aObj);
            String value_1 = Objects.nonNull(val) ? (String) val : "";

            cell_0.setCellValue(value_0);
            cell_0.setCellStyle(contentStyle);
            sheet.setColumnWidth(cell_0.getColumnIndex(), width);

            cell_1.setCellValue(value_1);
            cell_1.setCellStyle(contentStyle);
            sheet.setColumnWidth(cell_1.getColumnIndex(), width);
        };
        Integer index = 0, sheetIndex = 0;
        Row row = sheet.createRow(rowIndex++);
        for (Field field : fieldList) {
            Cell cell_0 = row.createCell(sheetIndex++);
            Cell cell_1 = row.createCell(sheetIndex++);
            consumer.accept(cell_0, cell_1, field);

            CellRangeAddress range = new CellRangeAddress(row.getRowNum(), row.getRowNum(), cell_1.getColumnIndex(), sheetIndex++);
            sheet.addMergedRegion(range);

            index++;
            if (index % 3 == 0) {
                row = sheet.createRow(rowIndex++);
                sheetIndex = 0;
            }
        }

        if (baseList.stream().anyMatch(var0 -> var0.getAnnotation(ExcelFormOne.class).summary())
                && showSummary) {
            Row summary = sheet.createRow(rowIndex++);
            Field field = baseList.stream().filter(var0 -> var0.getAnnotation(ExcelFormOne.class).summary())
                    .findFirst().get();

            Cell cell_0 = summary.createCell(0);
            Cell cell_1 = summary.createCell(1);
            consumer.accept(cell_0, cell_1, field);

            CellRangeAddress range = new CellRangeAddress(summary.getRowNum(), summary.getRowNum(), 1, 6);
            sheet.addMergedRegion(range);
        }

        return rowIndex;
    }

    private Integer formTwo(Sheet sheet, Integer rowIndex) {
        return this.form(sheet, bList, rowIndex);
    }

    private Integer formThree(Sheet sheet, Integer rowIndex) {
        return this.form(sheet, cList, rowIndex);
    }

    private Integer sign(Sheet sheet, Integer rowIndex) {
        if (!showSign) return rowIndex;
        rowIndex++;
        CellStyle style = styleMap.get(style_1);

        Row row = sheet.createRow(rowIndex++);

        Cell cell = row.createCell(maxFieldLength - 2);
        cell.setCellValue("签字");
        cell.setCellStyle(style);

        return rowIndex;
    }

    private Integer form(Sheet sheet, List list, Integer rowIndex) {
        if (Objects.isNull(list) || list.isEmpty()) return rowIndex;
        rowIndex++;

        CellStyle headStyle = styleMap.get(style_1);
        CellStyle contentStyle = styleMap.get(style_2);
        Class<?> classInfo = list.get(0).getClass();

        List<Field> fieldList = Stream.of(list.get(0).getClass().getDeclaredFields())
                .filter(var0 -> Objects.nonNull(var0.getAnnotation(ExcelFormTwo.class)))
                .collect(Collectors.toList());
        //排序
        if (fieldList.stream().anyMatch(var0 -> var0.getAnnotation(ExcelFormTwo.class).order() != 0)) {
            fieldList.sort(Comparator.comparingInt(var0 -> var0.getAnnotation(ExcelFormTwo.class).order()));
        }

        maxFieldLength = Math.max(maxFieldLength, fieldList.size());

        //head
        Row row = sheet.createRow(rowIndex++);
        Integer index = 0;
        for (Field field : fieldList) {
            ExcelFormTwo formTwo = field.getAnnotation(ExcelFormTwo.class);
            String headName = formTwo.headName();
            Cell cell = row.createCell(index++);
            cell.setCellValue(headName);
            cell.setCellStyle(headStyle);
            sheet.setColumnWidth(index, width);
        }

        //content
        for (Object o : list) {
            Integer index_1 = 0;
            Row row_1 = sheet.createRow(rowIndex++);
            for (Field field : fieldList) {
                Cell cell = row_1.createCell(index_1++);
                Object val = this.readValue(field, classInfo, o);
                cell.setCellValue(Objects.nonNull(val) ? (String) val : "");
                cell.setCellStyle(contentStyle);
            }
        }
        return rowIndex;
    }

    private Object readValue(Field field, Class aClass, Object obj) {
        Object val = null;
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), aClass);
            Method method = descriptor.getReadMethod();
            val = method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }


    //=====================================================
    private Integer style_0 = 0;
    private Integer style_1 = 1;
    private Integer style_2 = 2;
    Map<Integer, CellStyle> styleMap = new HashMap<>();

    private CellStyle style(Integer num, Workbook workbook) {
        CellStyle style = styleMap.get(num);
        if (Objects.isNull(style)) {
            if (style_0.equals(num)) style = this.style_0(workbook, (short) 15);//base head
            if (style_1.equals(num)) style = this.style_1(workbook, (short) 12);//head
            if (style_2.equals(num)) style = this.style_2(workbook, (short) 12);//content
            styleMap.put(num, style);
        }
        return style;
    }

    private CellStyle style_0(Workbook workbook, Short height){
        Font font = workbook.createFont();
        font.setFontHeightInPoints(height);
        font.setFontName("黑体");
        font.setBold(true);

        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }
    private CellStyle style_1(Workbook workbook, Short height){
        Font font = workbook.createFont();
        font.setFontHeightInPoints(height);
        font.setFontName("黑体");
        font.setBold(true);

        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }
    private CellStyle style_2(Workbook workbook, Short height){
        Font font = workbook.createFont();
        font.setFontHeightInPoints(height);
        font.setFontName("黑体");

        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }
    @FunctionalInterface
    private interface SBiConsumer<X, Y, Z> {
        void accept(X x, Y y, Z z);
    }
}
