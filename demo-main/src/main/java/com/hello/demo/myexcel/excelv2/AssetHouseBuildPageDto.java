package com.hello.demo.myexcel.excelv2;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 楼栋视图分页查询结果
 */
@Data
public class AssetHouseBuildPageDto {
    private Integer assetHouseId = new Random().nextInt(100);   //资产id
    private Integer projectId;  //资产项目id

    @AssetExcel(headerName = "资产名称")
    private String assetName = "aa";   //资产名称

    @AssetExcel(headerName = "资产编号")
    private String assetCode = "bb";   //资产编号

    @AssetExcel(headerName = "资产项目名称")
    private String projectName; //资产项目名称

    @AssetExcel(headerName = "丘地号")
    private String addressNo;  //丘地号 -- 数据中心

    @AssetExcel(headerName = "建筑年代")
    private String years;   //建筑年代 -- 数据中心

    @AssetExcel(headerName = "竣工时间")
    private String completionDate;   //竣工时间 -- 数据中心

    @AssetExcel(headerName = "建筑面积")
    private BigDecimal area;        //建筑面积

    @AssetExcel(headerName = "楼高")
    private Integer buildHeight;   //楼高 -- 数据中心

    @AssetExcel(headerName = "层数")
    private Integer floorNum = 100;   //层数

    @AssetExcel(headerName = "地上层数")
    private Integer upFloorNum; //地上层数 -- 数据中心

    @AssetExcel(headerName = "地上层数")
    private Integer downFloorNum;   //地上层数 -- 数据中心

    @AssetExcel(headerName = "资产数量")
    private Integer assetNum;   //资产数量， objectId+type下的所有数据

    @AssetExcel(headerName = "转运营面积")
    private BigDecimal transferOperationArea;   //转运营面积

    @AssetExcel(headerName = "自用面积")
    private BigDecimal selfUserArea;    //自用面积

    @AssetExcel(headerName = "闲置面积")
    private BigDecimal idleArea;    //闲置面积

    @AssetExcel(headerName = "占用面积")
    private BigDecimal occupationArea;  //占用面积

    @AssetExcel(headerName = "其它面积")
    private BigDecimal otherArea;   //其它面积

    @AssetExcel(headerName = "资产原值")
    private BigDecimal originalValue;   //资产原值

    private BigDecimal assetValuation;  //资产估值

    @AssetExcel(headerName = "市场价值")
    private BigDecimal marketValue;    //市场价值
}
