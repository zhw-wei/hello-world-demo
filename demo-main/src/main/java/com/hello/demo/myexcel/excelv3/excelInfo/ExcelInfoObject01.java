package com.hello.demo.myexcel.excelv3.excelInfo;

import com.hello.demo.myexcel.excelv3.ExplainExcelInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExcelInfoObject01 {

    @ExplainExcelInfo(line = 0)
    private String name;

    @ExplainExcelInfo(line = 1)
    private String type;

    @ExplainExcelInfo(line = 2)
    private Integer year;

    @ExplainExcelInfo(line = 3)
    private Integer month;

    @ExplainExcelInfo(line = 4)
    private String areaA;

    @ExplainExcelInfo(line = 5)
    private String areaB;

    @ExplainExcelInfo(line = 6)
    private String areaC;

    @ExplainExcelInfo(line = 7)
    private String moneyA;

    @ExplainExcelInfo(line = 8)
    private String moneyB;
}
