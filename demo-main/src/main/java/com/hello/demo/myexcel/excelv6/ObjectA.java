package com.hello.demo.myexcel.excelv6;

import lombok.Data;

@Data
public class ObjectA {
    @ExcelFormOne(headName = "订单号")
    private String val0 = "012345678901010100222";

    @ExcelFormOne(headName = "订单时间")
    private String val1 = "2020-01-01 01:01:01";

    @ExcelFormOne(headName = "申请人")
    private String val2 = "张三";

    @ExcelFormOne(headName = "调出仓库")
    private String val3 = "仓库001";

    @ExcelFormOne(headName = "调入仓库")
    private String val4 = "仓库002";

    @ExcelFormOne(headName = "总数量")
    private String val7 = "100.88";

    @ExcelFormOne(headName = "总金额")
    private String val5 = "1000.99";

    @ExcelFormOne(headName = "订单备注")
    private String val6 = "订单备注测试订单备注测试订单备注测试订单备注测试订单备注测试订单备注测试...";

    @ExcelFormOne(headName = "分类统计", summary = true)
    private String val8 = "统计结果: xxx xxx xxx xxx xxx xxx xxx xxx xxx xxx xxx xxx xxx xxx xxx";
}
