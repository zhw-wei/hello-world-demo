package com.hello.demo.myexcel.excelv2;

import lombok.Setter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateExcelV2<T> {
    private List<T> list;
    private String sheetName;
    private CellStyle headCellStyle;
    private CellStyle contentCellStyle;
    private CellStyle lastCellStyle;
    private CellStyle tailCellStyle;//尾部 -- 最后一行内容样式
    private List<String> tailList;//最后一行后面的内容 -- 合并行
    private Boolean lastContentRed = false;//最后一行内容是红色

    @Setter
    private String baseParent;

    public CreateExcelV2(List list, String sheetName) {
        this.list = list;
        this.sheetName = sheetName;
    }

    public CreateExcelV2(List<T> list, String sheetName, List<String> tailList) {
        this.list = list;
        this.sheetName = sheetName;
        this.tailList = tailList;
    }

    public CreateExcelV2(List<T> list, String sheetName, Boolean lastContentRed) {
        this.list = list;
        this.sheetName = sheetName;
        this.lastContentRed = lastContentRed;
    }

    public HSSFWorkbook createExcel() {
        HSSFWorkbook workbook = new HSSFWorkbook();

        this.initStyle(workbook);
        Sheet sheet = workbook.createSheet(sheetName);

        if (Objects.isNull(list) || list.isEmpty()) return workbook;
        if ((this.list = list.stream().filter(Objects::nonNull).collect(Collectors.toList())).isEmpty())
            return workbook;
        List<Field> fieldList = Stream.of(list.get(0).getClass().getDeclaredFields())
                .filter(var1 -> Objects.nonNull(var1.getAnnotation(ExcelInfo.class)))
                .collect(Collectors.toList());

        Boolean sParentVal = fieldList.stream().anyMatch(var0 -> var0.getAnnotation(ExcelInfo.class).sParentWidth() > 0);
        Boolean parentVal = fieldList.stream().anyMatch(var0 -> var0.getAnnotation(ExcelInfo.class).parentWidth() > 0);

        Integer rowIndex = baseHead(sheet, fieldList.size());
        rowIndex = sParentVal ? this.headV3(sheet, fieldList, rowIndex) :
                parentVal ? this.headV2(sheet, fieldList, rowIndex) : this.head(sheet, fieldList, rowIndex);
        rowIndex = this.content(sheet, rowIndex, fieldList);
        this.tail(sheet, rowIndex, fieldList.size());

        return workbook;
    }

    private Integer baseHead(Sheet sheet, Integer length) {
        Integer rowIndex = 0;
        if (Objects.nonNull(baseParent) && !baseParent.trim().equals("")) {
            Row row = sheet.createRow(rowIndex++);
            Cell cell = row.createCell(0);
            cell.setCellValue(baseParent);
            cell.setCellStyle(headCellStyle);
            CellRangeAddress range = new CellRangeAddress(row.getRowNum(), row.getRowNum(), cell.getColumnIndex(), length - 1);
            sheet.addMergedRegion(range);
        }
        return rowIndex;
    }

    private Integer head(Sheet sheet, List<Field> fieldList, Integer rowIndex) {
        Row row = sheet.createRow(rowIndex++);
        Integer index = 0;
        for (Field field : fieldList) {
            ExcelInfo assetExcel = field.getAnnotation(ExcelInfo.class);

            Cell cell = row.createCell(index);
            cell.setCellValue(assetExcel.headerName());
            cell.setCellStyle(headCellStyle);
            sheet.setColumnWidth(index++, assetExcel.width());
        }
        return rowIndex;
    }

    private Integer headV2(Sheet sheet, List<Field> fieldList, Integer rowIndex) {
        Row row_0 = sheet.createRow(rowIndex++);
        Row row_1 = sheet.createRow(rowIndex++);

        Integer parent = 0;
        for (int i = 0; i < fieldList.size(); i++) {
            ExcelInfo assetExcel = fieldList.get(i).getAnnotation(ExcelInfo.class);
            if (assetExcel.parentWidth() > 0) {
                parent = assetExcel.parentWidth();

                Cell parentCell = row_0.createCell(i);
                parentCell.setCellValue(assetExcel.parentVal());
                parentCell.setCellStyle(headCellStyle);
                CellRangeAddress rangeAddress = new CellRangeAddress(row_0.getRowNum(), row_0.getRowNum(), i, i + assetExcel.parentWidth() - 1);
                sheet.addMergedRegion(rangeAddress);
            }
            Cell cell = parent == 0 ? row_0.createCell(i) : row_1.createCell(i);
            cell.setCellValue(assetExcel.headerName());
            cell.setCellStyle(headCellStyle);
            sheet.setColumnWidth(i, assetExcel.width());
            if (parent == 0) {
                CellRangeAddress rangeAddress = new CellRangeAddress(row_0.getRowNum(), row_1.getRowNum(), i, i);
                sheet.addMergedRegion(rangeAddress);
            } else {
                parent--;
            }
        }

        return rowIndex;
    }

    private Integer headV3(Sheet sheet, List<Field> fieldList, Integer rowIndex) {
        Row row_0 = sheet.createRow(rowIndex++);
        Row row_1 = sheet.createRow(rowIndex++);
        Row row_2 = sheet.createRow(rowIndex++);

        Integer sParentWidth = 0;
        Integer parentWidth = 0;
        for (int i = 0; i < fieldList.size(); i++) {
            ExcelInfo excelInfo = fieldList.get(i).getAnnotation(ExcelInfo.class);
            if (excelInfo.sParentWidth() > 0) {
                sParentWidth = excelInfo.sParentWidth();
                Cell cell = row_0.createCell(i);
                cell.setCellValue(excelInfo.sParentVal());
                cell.setCellStyle(headCellStyle);
                CellRangeAddress rangeAddress = new CellRangeAddress(row_0.getRowNum(), row_0.getRowNum(), i, i + excelInfo.sParentWidth() - 1);
                sheet.addMergedRegion(rangeAddress);
            }
            if (excelInfo.parentWidth() > 0) {
                parentWidth = excelInfo.parentWidth();
                Row row = sParentWidth > 0 ? row_1 : row_0;

                Cell cell = row.createCell(i);
                cell.setCellValue(excelInfo.parentVal());
                cell.setCellStyle(headCellStyle);
                CellRangeAddress rangeAddress = new CellRangeAddress(row.getRowNum(), sParentWidth > 0 ? row.getRowNum() : row_1.getRowNum(),
                        i, i + excelInfo.parentWidth() - 1);
                sheet.addMergedRegion(rangeAddress);
            }

            Row row = parentWidth > 0 ? row_2 : row_0;
            Cell cell = row.createCell(i);
            cell.setCellValue(excelInfo.headerName());
            cell.setCellStyle(headCellStyle);
            sheet.setColumnWidth(i, excelInfo.width());
            if(parentWidth>0){
                parentWidth --;
            }else{
                CellRangeAddress rangeAddress = new CellRangeAddress(row.getRowNum(), row_2.getRowNum(), i, i);
                sheet.addMergedRegion(rangeAddress);
            }
            sParentWidth--;
        }

        return rowIndex;
    }

    private Integer content(Sheet sheet, Integer rowIndex, List<Field> fieldList) {
        for (int i = 0; i < list.size(); i++) {
            Object var0 = list.get(i);
            Boolean redContent = (list.size() - 1) == i && lastContentRed;

            Row row = sheet.createRow(rowIndex++);
            Integer index = 0;
            for (Field field : fieldList) {
                ExcelInfo assetExcel = field.getAnnotation(ExcelInfo.class);
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
                cell.setCellStyle(redContent ? lastCellStyle : contentCellStyle);
                cell.setCellValue((String) obj);
            }
        }
        return rowIndex;
    }

    private void tail(Sheet sheet, Integer rowIndex, Integer width) {
        if (Objects.isNull(tailList) || tailList.isEmpty()) return;
        tailList = tailList.stream().filter(Objects::nonNull).filter(var0 -> !var0.trim().equals("")).collect(Collectors.toList());
        for (String tail : tailList) {
            Row row = sheet.createRow(rowIndex);
            Cell cell = row.createCell(0);
            cell.setCellStyle(tailCellStyle);
            cell.setCellValue(tail);
            CellRangeAddress rangeAddress = new CellRangeAddress(rowIndex, rowIndex, 0, width - 1);
            sheet.addMergedRegion(rangeAddress);
            rowIndex++;
        }
    }

    private void initStyle(HSSFWorkbook workbook) {
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 15);
        headCellStyle = workbook.createCellStyle();
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headCellStyle.setFont(headerFont);

        contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentCellStyle.setAlignment(HorizontalAlignment.CENTER);

        Font tailFont = workbook.createFont();
        tailFont.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());

        lastCellStyle = workbook.createCellStyle();
        lastCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        lastCellStyle.setAlignment(HorizontalAlignment.CENTER);
        lastCellStyle.setFont(tailFont);

        tailCellStyle = workbook.createCellStyle();
        tailCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        tailCellStyle.setFont(tailFont);
    }
}
