package com.hello.demo.myexcel.excelv2;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StoreGoodsRelDetailV2Dto {

    @ExcelInfo(headerName = "分类名称", parentVal = "TEST01", parentWidth = 3)
    private String goodsTypeName = "aaa";

    @ExcelInfo(headerName = "物料编码")
    private Integer goodsInstCode = 1;

    @ExcelInfo(headerName = "物料名称")
    private String goodsName = "ccc";

    @ExcelInfo(headerName = "仓库名称")
    private String storeName = "ddd";

    @ExcelInfo(headerName = "物料单位", parentVal = "TEST02", parentWidth = 3)
    private String unitName = "eee";

    @ExcelInfo(headerName = "物料规格")
    private String goodsInstModel = "fff";

    @ExcelInfo(headerName = "物料品牌")
    private String goodsInstBrand = "ggg";

    @ExcelInfo(headerName = "供应商")
    private String supplierName = "hhh";

    @ExcelInfo(headerName = "入库时间", parentVal = "TEST03", parentWidth = 3)
    private String inputTime = "mmm";

    @ExcelInfo(headerName = "税率")
    private String rate = "nnn";

    @ExcelInfo(headerName = "含税价")
    private String ratePrice = "ooo";

    @ExcelInfo(headerName = "无税单价")
    private String price = "ppp";

    @ExcelInfo(headerName = "数量", parentVal = "TEST04", parentWidth = 3)
    private String count = "qqq";

    @ExcelInfo(headerName = "含税金额")
    private String totalMoney = "rrr";

    @ExcelInfo(headerName = "无税金额")
    private String money = "sss";

    @ExcelInfo(headerName = "税额")
    private String rateMoney = "ttt";

    @ExcelInfo(headerName = "备注", parentVal = "TEST05", parentWidth = 2)
    private String mark = "uuu";

    @ExcelInfo(headerName = "申请人")
    private String applyUserName = "vvv";

    private String summary = "www";//汇总
}
