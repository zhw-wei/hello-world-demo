package com.hello.demo.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author: zhaohw
 * @date: 2022.02.09 下午 3:32
 */
public class ExcelUtil {

    static Function<Date, ZonedDateTime> DATE_TO_ZONED = date ->
            ZonedDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    static Function<Date, String> DATE_TO_STRING_YYYY_MM_DD = date -> {
        if (Objects.isNull(date)) return "";
        return DATE_TO_ZONED.apply(date).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    };

    public static String readAsString(int colIndex, Row row) {
        String value = "";
        try {
            Cell cell = row.getCell(colIndex);
            if (Objects.isNull(cell) || cell.getCellType() == CellType.BLANK) {
                return "";
            }

            CellType cellType = cell.getCellType();
            switch (cellType){
                case NUMERIC: value = String.valueOf(cell.getNumericCellValue());break;
                case STRING: value = cell.getStringCellValue().trim();break;
                case FORMULA: value = cell.getCellFormula(); break;
                default: value = ""; break;
            }

            //有可能处理成日期格式
            if (cell.getCellType() != CellType.STRING && DateUtil.isCellDateFormatted(cell)) {
                Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                return DATE_TO_STRING_YYYY_MM_DD.apply(date);
            }

        } catch (Exception e) {
            System.out.println((row.getRowNum()+1) + "行," + (colIndex+1) + "列, 解析异常, value=" + value);
        }
        return value;
    }
}
