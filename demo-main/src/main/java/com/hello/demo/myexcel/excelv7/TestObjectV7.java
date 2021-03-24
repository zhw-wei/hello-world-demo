package com.hello.demo.myexcel.excelv7;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.hello.demo.myexcel.excelv7.ExcelContents.*;

@NoArgsConstructor
@Data
public class TestObjectV7 {

    @ExcelInfo(headName = "用途类型", sort = 0, flag = {a1, b1, a2, b2})
    private String dimensionName;

    @ExcelInfo(headName = "上级表编号", sort = 1, flag = {c1})
    private String parentEquipmentCode;

    @ExcelInfo(headName = "仪表等级", sort = 2, flag = {c1})
    private String equipmentLevelName;

    @ExcelInfo(headName = "仪表编号", sort = 3, flag = {c1})
    private String equipmentInstCode;

    @ExcelInfo(headName = "仪表名称", sort = 4, flag = {c1})
    private String equipmentInstName;

    @ExcelInfo(headName = "机构名称", sort = 5, flag = {c2, d2, e2})
    private String organName;

    @ExcelInfo(headName = "项目名称", sort = 6, flag = {f2, g2, h2})
    private String projectName;

    @ExcelInfo(headName = "所属机构", sort = 7, flag = {f2, g2, h2})
    private String belongOrganName;

    @ExcelInfo(headName = "同比用量", sort = 10, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String yearConsumption;

    @ExcelInfo(headName = "环比用量", sort = 20, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String sequentialConsumption;

    @ExcelInfo(headName = "本期用量", sort = 30, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String currentConsumption;

    @ExcelInfo(headName = "同比用量升降率", sort = 40, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String yearClimb;

    @ExcelInfo(headName = "环比用量升降率", sort = 50, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String sequentialClimb;

    @ExcelInfo(headName = "本期节能降幅排名", sort = 51, flag = {c2, d2, e2, f2, g2, h2})
    private String currentEnergyDeclineRanking;

    @ExcelInfo(headName = "服务面积（㎡）", sort = 60, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String serviceArea;

    @ExcelInfo(headName = "单价", sort = 70, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String unitPrice;

    @ExcelInfo(headName = "环比单平方米用量", width = 6000, sort = 80, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String yearMeterConsumption;

    @ExcelInfo(headName = "本期单平方米用量", width = 6000, sort = 90, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String currentMeterConsumption;

    @ExcelInfo(headName = "环比单平方米金额", width = 6000, sort = 100, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String yearMeterMoney;

    @ExcelInfo(headName = "本期单平方米金额", width = 6000, sort = 110, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String currentMeterMoney;

    @ExcelInfo(headName = "本期单平方米金额排名", width = 8000, sort = 120, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String currentMeterMoneyRanking;

    @ExcelInfo(headName = "本期指标完成率", sort = 130, flag = {a1, a2, d2, g2})
    private String currentIndexRate;

    @ExcelInfo(headName = "指标完成率排名", sort = 140, flag = {a1, a2, d2, g2})
    private String indexRateRanking;

    @ExcelInfo(headName = "环比单平方米金额升降率", width = 8000, sort = 150, flag = {a1, b1, c1, a2, b2, c2, d2, e2, f2, g2, h2})
    private String yearMeterMoneyClimb;

    @ExcelInfo(headName = "转售占比", sort = 160, flag = {c2, f2})
    private String resaleProportion;

    @ExcelInfo(headName = "转售占比排名", sort = 170, flag = {c2, f2})
    private String resaleRanking;

}
