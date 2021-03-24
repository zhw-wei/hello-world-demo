package com.hello.demo.myexcel.fastExcel.core;

import com.hello.demo.myexcel.fastExcel.annotation.FieldDescription;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExcelExportConfig {

    /**
     * 展示的列
     */
    private List<String> displayFields;

    /**
     * 内容样式
     */
    private Map<String, CellStyle> styleMap = new HashMap<>();

    /**
     * 默认样式
     */
    private CellStyle defaultCellStyle;


    public ExcelExportConfig(List<String> displayFields){
        this.displayFields = displayFields;
    }
    public ExcelExportConfig(){

    }

    public Boolean checkDisplay(String field){
        if(Objects.isNull(displayFields) || displayFields.isEmpty()) return true;
        return displayFields.contains(field);
    }

    public void setStyleMap(List<Field> fieldList, HSSFWorkbook workbook, Boolean displayHead, Boolean sameObject){
        if(Objects.isNull(defaultCellStyle)) defaultCellStyle = workbook.createCellStyle();
        if(sameObject && (Objects.nonNull(displayHead) || Objects.nonNull(styleMap))) return;

        styleMap.clear();
        if(displayHead){
            fieldList.forEach(field -> {
                FieldDescription desc = field.getAnnotation(FieldDescription.class);
                if(Objects.isNull(desc)) return;

                String headKey = this.styleMapKey(desc, true);
                if(Objects.isNull(styleMap.get(headKey))){
                    Font font = workbook.createFont();
                    font.setFontHeight((short) desc.headFontSize());

                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setFont(font);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);

                    styleMap.put(headKey, cellStyle);
                }
            });
        }

        fieldList.forEach(field -> {
            FieldDescription desc = field.getAnnotation(FieldDescription.class);
            if(Objects.isNull(desc)) return;

            String headKey = this.styleMapKey(desc, false);
            if(Objects.isNull(styleMap.get(headKey))){
                Font font = workbook.createFont();
                font.setFontHeight((short) desc.fontSize());

                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setFont(font);
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);

                styleMap.put(headKey, cellStyle);
            }
        });
    }

    public CellStyle getStyleHead(FieldDescription desc){
        return styleMap.getOrDefault(this.styleMapKey(desc, true), defaultCellStyle);
    }
    public CellStyle getStyleContent(FieldDescription desc){
        return styleMap.getOrDefault(this.styleMapKey(desc, false), defaultCellStyle);
    }

    private String styleMapKey(FieldDescription desc, Boolean head){
        if(head) return desc.headFontSize() + "";
        return desc.fontSize() + "";
    }
}
