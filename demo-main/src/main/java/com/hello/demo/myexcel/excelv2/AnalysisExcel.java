package com.hello.demo.myexcel.excelv2;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalysisExcel<T>{

    private InputStream inputStream;
    private List<T> resultList;
    private Class<T> tClass;

    public AnalysisExcel(InputStream inputStream, Class<T> tClass){
        this.inputStream = inputStream;
        this.resultList = new ArrayList<>();
        this.tClass = tClass;
    }

    public List<T> analysisExcel() throws IOException {
        Workbook workbook = WorkbookFactory.create(inputStream);
        //key: column name, value: field name
        Map<String, Field> fieldMap = Stream.of(tClass.getDeclaredFields())
            .filter(var0 -> Objects.nonNull(var0.getDeclaredAnnotation(AssetExcel.class))).collect(Collectors.toMap(var1 -> var1.getDeclaredAnnotation(AssetExcel.class).headerName(), var2 -> var2, (var3, var4) -> var3));

        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        while(cellIterator.hasNext()){
            Cell cell = cellIterator.next();
        }

        return resultList;
    }
}
