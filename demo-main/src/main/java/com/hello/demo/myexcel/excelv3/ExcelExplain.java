package com.hello.demo.myexcel.excelv3;

import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * excel解析
 */
public class ExcelExplain<T> {
    private InputStream inputStream;//文件流
    private Class<T> classInfo;//转换的对象

    int i = 0;

    private BiPredicate<Class, Class> CLASS_CHECK = (var0, var1) -> var0.getName().equals(var1.getName());
    private String TEMPLATE = "第%s行，第%s列，数据类型要求是%s";
    Function<BigDecimal, String> DECIMAL_FUN = var0 ->
            Objects.isNull(var0) ? "0.00" : var0.setScale(4, RoundingMode.DOWN).stripTrailingZeros().toPlainString();

    public ExcelExplain(InputStream inputStream, Class<T> classInfo) {
        this.inputStream = inputStream;
        this.classInfo = classInfo;
    }

    /**
     * @param sheetAt   工作表， 从 1 开始
     * @param startLine 开始行， 从 1 开始
     * @return
     * @throws Exception
     */
    public ExplainResult<T> explain(int sheetAt, int startLine) throws Exception {

        startLine = Objects.isNull(startLine) || startLine < 1 ? 1 : startLine;
        List<T> resultList = new ArrayList<>();
        List<Field> fieldList = Stream.of(classInfo.getDeclaredFields())
                .filter(field -> Objects.nonNull(field.getDeclaredAnnotation(ExplainExcelInfo.class))).collect(Collectors.toList());
        Map<Integer, Field> fieldMap = fieldList.stream()
                .collect(Collectors.toMap(var0 -> var0.getDeclaredAnnotation(ExplainExcelInfo.class).line(),
                        var1 -> var1, (var2, var3) -> var2));

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(sheetAt - 1);

        List<String> errorMsgList = new ArrayList<>();
        Integer rowNum = sheet.getLastRowNum();
        for (int i = startLine - 1; i <= rowNum; i++) {//读取行

            T obj = classInfo.getDeclaredConstructor().newInstance();//实例化
            Row row = sheet.getRow(i);
            for (int j = 0; j < fieldList.size(); j++) {//读取行
                Field field = fieldMap.get(j);
                if (Objects.isNull(field)) continue;

                //设置结果
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), classInfo);
                Method method = descriptor.getWriteMethod();

                Class type = field.getType();
                try {
                    Cell cell = row.getCell(j);
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            Double result = cell.getNumericCellValue();
                            if (CLASS_CHECK.test(type, String.class)) {
                                method.invoke(obj, DECIMAL_FUN.apply(new BigDecimal(result)));
                            } else if (CLASS_CHECK.test(type, Integer.class)) {
                                method.invoke(obj, result.intValue());
                            } else if (CLASS_CHECK.test(type, Float.class)) {
                                method.invoke(obj, result.floatValue());
                            } else if (CLASS_CHECK.test(type, BigDecimal.class)) {
                                method.invoke(obj, new BigDecimal(result));
                            } else {
                                method.invoke(obj, result);
                            }
                            break;
                        case STRING:
                            method.invoke(obj, cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                } catch (Exception ex) {
                    System.out.println("ex: " + ex);
                    if (this.i < 10) ex.printStackTrace();
                    this.i++;
                    errorMsgList.add(String.format(TEMPLATE, i + 1, j + 1, type.getSimpleName()));
                }
            }
            resultList.add(obj);
        }

        ExplainResult<T> explainResult = new ExplainResult<>();
        explainResult.setErrorMsg(errorMsgList);
        explainResult.resultList = resultList;

        return explainResult;
    }

    @Data
    public static class ExplainResult<T> {
        private List<String> errorMsg;//错误信息
        private List<T> resultList;//解析结果
    }
}
