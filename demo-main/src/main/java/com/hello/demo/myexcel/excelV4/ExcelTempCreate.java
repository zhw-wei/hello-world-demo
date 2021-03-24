package com.hello.demo.myexcel.excelV4;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelTempCreate<T> {
    private Class<T> classInfo;
    private String sheetName;

    public ExcelTempCreate(Class<T> classInfo, String sheetName) {
        this.classInfo = classInfo;
        this.sheetName = sheetName;
    }

    public HSSFWorkbook createTemplate() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        List<Field> fieldList = Stream.of(classInfo.getDeclaredFields())
                .filter(var0 -> Objects.nonNull(var0.getDeclaredAnnotation(ExcelTempInfo.class)))
                .collect(Collectors.toList());
        Map<ExcelColor, CellStyle> styleMap = new HashMap<>();
        Consumer<ExcelColor> createStyle = color -> styleMap.putIfAbsent(color,
                styleMap.getOrDefault(color, this.createStyle(color, workbook)));

        fieldList.forEach(field -> {
            ExcelTempInfo info = field.getAnnotation(ExcelTempInfo.class);
            createStyle.accept(info.color());
            createStyle.accept(info.parentColor());
        });

        Integer rowIndex = 0;
        rowIndex = fieldList.stream().anyMatch(field -> field.getAnnotation(ExcelTempInfo.class).parentWidth() > 0) ?
                this.createHeadV2(sheet, fieldList, rowIndex, styleMap) : this.createHead(sheet, fieldList, rowIndex, styleMap);
        this.createComboBox(sheet, fieldList, rowIndex);

        return workbook;
    }

    private Integer createHead(Sheet sheet, List<Field> fieldList,
                               Integer rowIndex, Map<ExcelColor, CellStyle> styleMap) {

        Row row = sheet.createRow(rowIndex++);
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            ExcelTempInfo info = field.getAnnotation(ExcelTempInfo.class);

            Cell cell = row.createCell(i);
            cell.setCellStyle(styleMap.get(info.color()));
            cell.setCellValue(info.headerName());
            sheet.setColumnWidth(i, info.width());
        }

        return rowIndex;
    }

    private Integer createHeadV2(Sheet sheet, List<Field> fieldList,
                                 Integer rowIndex, Map<ExcelColor, CellStyle> styleMap) {
        Row row_0 = sheet.createRow(rowIndex++);
        Row row_1 = sheet.createRow(rowIndex++);

        Integer parent = 0;
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            ExcelTempInfo info = field.getAnnotation(ExcelTempInfo.class);
            if (info.parentWidth() > 0) {
                parent = info.parentWidth();
                Cell cell = row_0.createCell(i);
                cell.setCellValue(info.parentVal());
                cell.setCellStyle(styleMap.get(info.parentColor()));
                CellRangeAddress range = new CellRangeAddress(0, 0, i, i + info.parentWidth() - 1);
                sheet.addMergedRegion(range);
            }
            Cell cell = parent == 0 ? row_0.createCell(i) : row_1.createCell(i);
            cell.setCellValue(info.headerName());
            cell.setCellStyle(styleMap.get(info.color()));
            sheet.setColumnWidth(i, info.width());
            if (parent == 0) {
                CellRangeAddress range = new CellRangeAddress(0, 1, i, i);
                sheet.addMergedRegion(range);
            } else {
                parent--;
            }
        }
        return rowIndex;
    }

    private void createComboBox(Sheet sheet, List<Field> fieldList, Integer rowIndex) {
        if (fieldList.stream().noneMatch(field ->
                field.getAnnotation(ExcelTempInfo.class).comboBox().length > 0)) return;
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            ExcelTempInfo info = field.getAnnotation(ExcelTempInfo.class);
            if (info.comboBox().length == 0) continue;
            //下拉框内容
            DVConstraint constraint = DVConstraint.createExplicitListConstraint(info.comboBox());
            //下拉作用域
            CellRangeAddressList range = new CellRangeAddressList(rowIndex, rowIndex + 10000, i, i);
            //绑定下拉框和作用域
            HSSFDataValidation validation = new HSSFDataValidation(range, constraint);
            //对sheet页生效
            sheet.addValidationData(validation);
        }
    }

    private CellStyle createStyle(ExcelColor color, Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 15);
        font.setColor(colorMap.get(color).getIndex());

        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        return style;
    }

    private Map<ExcelColor, HSSFColor.HSSFColorPredefined> colorMap =
            new HashMap<ExcelColor, HSSFColor.HSSFColorPredefined>() {{
                put(ExcelColor.BLACK, HSSFColor.HSSFColorPredefined.BLACK);
                put(ExcelColor.RED, HSSFColor.HSSFColorPredefined.RED);
                put(ExcelColor.YELLOW, HSSFColor.HSSFColorPredefined.YELLOW);
                put(ExcelColor.BLUE, HSSFColor.HSSFColorPredefined.BLUE);
            }};

}
