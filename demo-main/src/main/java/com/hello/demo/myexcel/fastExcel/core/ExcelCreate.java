package com.hello.demo.myexcel.fastExcel.core;

import com.hello.demo.myexcel.fastExcel.Excel;
import com.hello.demo.myexcel.fastExcel.ExcelData;
import com.hello.demo.myexcel.fastExcel.annotation.ExcelInfo;
import com.hello.demo.myexcel.fastExcel.annotation.FieldDescription;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ExcelCreate implements Excel {

    private ExcelExportConfig excelExportConfig;
    private List<ExcelData> excelDataList;
    private Boolean sameObject;

    public ExcelCreate(ExcelExportConfig excelExportConfig, List<ExcelData> excelDataList){
        this.excelExportConfig = excelExportConfig;
        this.excelDataList = excelDataList;
        this.sameObject = excelDataList.stream()
                .filter(var0 -> Objects.nonNull(var0.getExcelData()) && !var0.getExcelData().isEmpty())
                .map(var1 -> var1.getExcelData().get(0).getClass().getName())
                .collect(Collectors.toSet()).size()==1;
    }
    public ExcelCreate(List<ExcelData> excelDataList){
        this(new ExcelExportConfig(), excelDataList);
    }

    @Override
    public HSSFWorkbook createExcel() throws Exception{
        if(Objects.isNull(excelDataList) || excelDataList.size()==0)
            throw new FastExcelException("The excel data list can not be null or empty!");
        if(excelDataList.stream().map(ExcelData::getSheetName)
                .filter(Objects::isNull).count()!=0)
            throw new FastExcelException("The sheet name can not be null!");
        excelDataList.forEach(var0 -> var0.setSheetName(var0.getSheetName().trim()));
        if(excelDataList.stream().map(ExcelData::getSheetName)
                .collect(Collectors.toSet()).size()<excelDataList.size())
            throw new FastExcelException("There is one more sheet name same!");

        HSSFWorkbook workbook = new HSSFWorkbook();

        for (ExcelData excelData : excelDataList) {
            Sheet sheet = workbook.createSheet(excelData.getSheetName());
            if (Objects.isNull(excelData.getExcelData())
                    || excelData.getExcelData().size() <= 0) continue;

            ExcelInfo excelInfo = excelData.getExcelData().get(0).getClass().getAnnotation(ExcelInfo.class);
            Boolean displayHead = Objects.nonNull(excelInfo) ? excelInfo.displayHeader() : true;

            //所有要生成的列
            List<Field> fieldList = Stream.of(excelData.getExcelData().get(0).getClass().getDeclaredFields())
                    .filter(var0 -> excelExportConfig.checkDisplay(var0.getName()))
                    .collect(Collectors.toList());
            excelExportConfig.setStyleMap(fieldList, workbook, displayHead, sameObject);
            Integer rowIndex = this.head(displayHead, sheet, fieldList);
            this.content(rowIndex, excelData, sheet, fieldList);

        }
        return workbook;
    }

    private Integer head(Boolean displayHead, Sheet sheet, List<Field> fieldList){
        if(!displayHead) return 0;
        Row row = sheet.createRow(0);
        Integer index = 0;
        Integer rowHeight = FieldDescription.minHeadHeight;
        for (Field field : fieldList) {
            FieldDescription fieldDesc = field.getAnnotation(FieldDescription.class);

            if (Objects.isNull(fieldDesc)) continue;
            Cell cell = row.createCell(index);
            cell.setCellValue(fieldDesc.headerName());
            cell.setCellStyle(excelExportConfig.getStyleHead(fieldDesc));
            sheet.setColumnWidth(index++, Math.max(FieldDescription.minWidth, fieldDesc.width()));

            rowHeight = Math.max(fieldDesc.headHeight(), rowHeight);
        }
        row.setHeight(rowHeight.shortValue());

        return 1;
    }

    private void content(Integer rowIndex, ExcelData excelData, Sheet sheet, List<Field> fieldList){
        for (Object data : excelData.getExcelData()) {
            Row row = sheet.createRow(rowIndex++);
            Integer index = 0;
            Integer rowHeight = FieldDescription.minHeight;
            for (Field field : fieldList) {
                FieldDescription fieldDesc = field.getAnnotation(FieldDescription.class);
                if (Objects.isNull(fieldDesc)) continue;

                Object obj = null;
                try {
                    PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(),
                            data.getClass());
                    Method method = descriptor.getReadMethod();
                    obj = method.invoke(data);
                    obj = Objects.nonNull(obj) ? obj : fieldDesc.defaultValue();
                } catch (Exception e) {
                    log.error("exception: ", e);
                }

                Cell cell = row.createCell(index);
                cell.setCellValue(ToString.to(obj, fieldDesc));
                cell.setCellStyle(excelExportConfig.getStyleContent(fieldDesc));
                sheet.setColumnWidth(index++, Math.max(FieldDescription.minWidth, fieldDesc.width()));

                rowHeight = Math.max(fieldDesc.height(), rowHeight);
            }
            row.setHeight(rowHeight.shortValue());
        }
    }
}
