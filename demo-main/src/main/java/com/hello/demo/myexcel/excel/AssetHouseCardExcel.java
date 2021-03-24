package com.hello.demo.myexcel.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 房屋卡片导出excel
 */
@Slf4j
public class AssetHouseCardExcel {

    private static final String KEY = "assetHouseCardNoKey";
    private static final Integer ORD_BASE = 0, ABS_BASE = 0;

    private static final Integer DEFAULT_WIDTH = 6000;
    private static final Integer MIN_WIDTH = 3600;

    private static final Integer DEFAULT_HEIGHT = 400;
    private static final Integer MAX_HEIGHT = 600;

    Function<Integer, Integer> ord = var0 -> ORD_BASE + var0;
    Function<Integer, Integer> abs = var1 -> ABS_BASE + var1;
    Supplier<String> key = () -> KEY + UUID.randomUUID().toString();

    Supplier<Map<String, RowInfo>> ROW_INFO_MAP = () -> {
        Integer rowIndex = 0;
        Map<String, RowInfo> rowInfoMap = new HashMap<>();

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex++), abs.apply(0), DEFAULT_WIDTH, MAX_HEIGHT,
                "房   屋   基   础   信   息", true, new MergeInfo(0, 6)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "项目/楼栋/房屋名称"));
        rowInfoMap.put("name", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "项目/楼栋/房屋地址"));
        rowInfoMap.put("address", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "土地面积"));
        rowInfoMap.put("landArea", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "建筑面积"));
        rowInfoMap.put("buildArea", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "用途")); //住宅/非住宅
        rowInfoMap.put("useType", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "结构"));
        rowInfoMap.put("structure", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "总层数"));
        rowInfoMap.put("totalFloor", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "所在楼层"));
        rowInfoMap.put("floor", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "权属性质"));   //产权/使用权
        rowInfoMap.put("ownerType", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "权证号/使用权证号"));
        rowInfoMap.put("warrantNum", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), DEFAULT_WIDTH, (int) (MAX_HEIGHT * 2.5),
                "所有权人(产权)/房屋所有权单位、被委托管理单位、承租人(使用权)"));
        rowInfoMap.put("ownerShip", new RowInfo(ord.apply(rowIndex++), abs.apply(1), new MergeInfo(0, 5)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "土地权利性质"));  //出让/划拨
        rowInfoMap.put("areaType", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "土地证号"));
        rowInfoMap.put("areaCardNo", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "接管日期"));
        rowInfoMap.put("takeOverTime", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "接管时房屋状况"));    //闲置、经营、被占用
        rowInfoMap.put("takeOverStatus", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "户型"));  //几室几厅
        rowInfoMap.put("apartmentType", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "装修情况"));
        rowInfoMap.put("ownerShipStatus", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "建筑年代"));
        rowInfoMap.put("buildYear", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "户数"));
        rowInfoMap.put("houseHold", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "移交物业日期"));
        rowInfoMap.put("transProDate", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "移交运营日期"));
        rowInfoMap.put("transOperateDate", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex++), abs.apply(0), DEFAULT_WIDTH, MAX_HEIGHT,
                "附   属   设   施   设   备", true, new MergeInfo(0, 6)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "名称"));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(1), MIN_WIDTH, DEFAULT_HEIGHT, "有/无"));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(2), MIN_WIDTH, DEFAULT_HEIGHT, "是否欠费"));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(3), MIN_WIDTH, DEFAULT_HEIGHT, "交接时读数"));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "表号"));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(5), MIN_WIDTH, DEFAULT_HEIGHT, "名称"));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex++), abs.apply(6), MIN_WIDTH, DEFAULT_HEIGHT, "有/无及情况"));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "水"));
        rowInfoMap.put("water0", new RowInfo(ord.apply(rowIndex), abs.apply(1), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("water1", new RowInfo(ord.apply(rowIndex), abs.apply(2), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("water2", new RowInfo(ord.apply(rowIndex), abs.apply(3), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("water3", new RowInfo(ord.apply(rowIndex), abs.apply(4)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(5), MIN_WIDTH, DEFAULT_HEIGHT, "门"));
        rowInfoMap.put("water4", new RowInfo(ord.apply(rowIndex++), abs.apply(6), MIN_WIDTH, DEFAULT_HEIGHT));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "电"));
        rowInfoMap.put("electric0", new RowInfo(ord.apply(rowIndex), abs.apply(1), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("electric1", new RowInfo(ord.apply(rowIndex), abs.apply(2), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("electric2", new RowInfo(ord.apply(rowIndex), abs.apply(3), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("electric3", new RowInfo(ord.apply(rowIndex), abs.apply(4)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(5), MIN_WIDTH, DEFAULT_HEIGHT, "窗"));
        rowInfoMap.put("electric4", new RowInfo(ord.apply(rowIndex++), abs.apply(6), MIN_WIDTH, DEFAULT_HEIGHT));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "暖"));
        rowInfoMap.put("warm0", new RowInfo(ord.apply(rowIndex), abs.apply(1), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("warm1", new RowInfo(ord.apply(rowIndex), abs.apply(2), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("warm2", new RowInfo(ord.apply(rowIndex), abs.apply(3), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("warm3", new RowInfo(ord.apply(rowIndex), abs.apply(4)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(5), MIN_WIDTH, DEFAULT_HEIGHT, "钥匙"));
        rowInfoMap.put("warm4", new RowInfo(ord.apply(rowIndex++), abs.apply(6), MIN_WIDTH, DEFAULT_HEIGHT));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "燃气"));
        rowInfoMap.put("gas0", new RowInfo(ord.apply(rowIndex), abs.apply(1), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("gas1", new RowInfo(ord.apply(rowIndex), abs.apply(2), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("gas2", new RowInfo(ord.apply(rowIndex), abs.apply(3), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("gas3", new RowInfo(ord.apply(rowIndex), abs.apply(4)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(5), MIN_WIDTH, DEFAULT_HEIGHT, "电梯"));
        rowInfoMap.put("gas4", new RowInfo(ord.apply(rowIndex++), abs.apply(6), MIN_WIDTH, DEFAULT_HEIGHT));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "其它"));
        rowInfoMap.put("other0", new RowInfo(ord.apply(rowIndex), abs.apply(1), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("other1", new RowInfo(ord.apply(rowIndex), abs.apply(2), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("other2", new RowInfo(ord.apply(rowIndex), abs.apply(3), MIN_WIDTH, DEFAULT_HEIGHT));
        rowInfoMap.put("other3", new RowInfo(ord.apply(rowIndex), abs.apply(4)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(5), MIN_WIDTH, DEFAULT_HEIGHT, "其它"));
        rowInfoMap.put("other4", new RowInfo(ord.apply(rowIndex++), abs.apply(6), MIN_WIDTH, DEFAULT_HEIGHT));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex++), abs.apply(0), DEFAULT_WIDTH, MAX_HEIGHT,
                "资     产     价     值", true, new MergeInfo(0, 6)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "资产原值", new MergeInfo(1, 0)));
        rowInfoMap.put("originalValue", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(1, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "评估价值(成本法)"));
        rowInfoMap.put("assetValue0", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "评估价值(市场法)"));
        rowInfoMap.put("assetValue1", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "有无债权"));
        rowInfoMap.put("hasClaims", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "债权金额"));
        rowInfoMap.put("claims", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "有无债务"));
        rowInfoMap.put("hasDebt", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "债务金额"));
        rowInfoMap.put("debt", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex++), abs.apply(0), DEFAULT_WIDTH, MAX_HEIGHT,
                "物      业      公      司", true, new MergeInfo(0, 6)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "是否有物业"));
        rowInfoMap.put("hasProperty", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "物业公司名称"));
        rowInfoMap.put("propertyName", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));

        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(0), "物业费单价"));
        rowInfoMap.put("unitPrice", new RowInfo(ord.apply(rowIndex), abs.apply(1), new MergeInfo(0, 2)));
        rowInfoMap.put(key.get(), new RowInfo(ord.apply(rowIndex), abs.apply(4), "是否欠费"));
        rowInfoMap.put("arrears", new RowInfo(ord.apply(rowIndex++), abs.apply(5), new MergeInfo(0, 1)));
        return rowInfoMap;
    };

    /**
     * 合并的坐标，以及合并的高度和宽度
     */
    @AllArgsConstructor
    private static class MergeInfo {
        private Integer down;   //向下合并的表格数
        private Integer right;  //向右合并的表格数
    }

    /**
     * 坐标位置，以及坐标内容
     */
    @AllArgsConstructor
    private class RowInfo {
        private Integer ord;    //纵坐标
        private Integer abs;    //横坐标
        private Integer width = DEFAULT_WIDTH;  //宽度
        private Integer height = DEFAULT_HEIGHT; //高度
        private String value;   //值
        private Boolean special = false;  //特殊字体和大小
        private MergeInfo mergeInfo;

        RowInfo(Integer ord, Integer abs, Integer width, Integer height) {
            this.ord = ord;
            this.abs = abs;
            this.width = width;
            this.height = height;
        }

        RowInfo(Integer ord, Integer abs) {
            this.ord = ord;
            this.abs = abs;
        }

        RowInfo(Integer ord, Integer abs, MergeInfo mergeInfo) {
            this.ord = ord;
            this.abs = abs;
            this.mergeInfo = mergeInfo;
        }

        RowInfo(Integer ord, Integer abs, Integer width, Integer height, String value) {
            this.ord = ord;
            this.abs = abs;
            this.width = width;
            this.height = height;
            this.value = value;
        }

        RowInfo(Integer ord, Integer abs, String value) {
            this.ord = ord;
            this.abs = abs;
            this.value = value;
        }

        RowInfo(Integer ord, Integer abs, String value, MergeInfo mergeInfo) {
            this.ord = ord;
            this.abs = abs;
            this.value = value;
            this.mergeInfo = mergeInfo;
        }
    }

    /**
     * excel中填入的数据
     */
    @Getter
    @Setter
    private class CardDataInfo {
        private String sheetName;
        private String name;    //项目/楼栋/房屋名称
        private String address; //项目/楼栋/房屋地址
        private BigDecimal landArea;    //土地面积
        private BigDecimal buildArea;   //建筑面积
        private String useType; //用途，住宅/非住宅
        private String structure;   //结构
        private Integer totalFloor; //总层数
        private Integer floor;  //所在楼层
        private String ownerType;   //权属性质，产权/使用权
        private String warrantNum;  //权证号/使用权证号
        private String ownerShip;
        private String areaType;    //土地权利性质
        private String areaCardNo;  //土地证号
        private String takeOverTime;    //接管日期
        private String takeOverStatus;  //接管时房屋状况
        private String apartmentType;   //户型
        private String ownerShipStatus; //装修情况
        private String buildYear;   //建筑年代
        private Integer houseHold;   //户数
        private String transProDate;    //移交物业日期
        private String transOperateDate;    //移交运营日期

        private String water0 = "water0";
        private String water1 = "water1";
        private String water2 = "water2";
        private String water3 = "water3";
        private String water4 = "water4";

        private String electric0 = "electric0";
        private String electric1 = "electric1";
        private String electric2 = "electric2";
        private String electric3 = "electric3";
        private String electric4 = "electric4";

        private String warm0 = "warm0";
        private String warm1 = "warm1";
        private String warm2 = "warm2";
        private String warm3 = "warm3";
        private String warm4 = "warm4";

        private String gas0 = "gas0";
        private String gas1 = "gas1";
        private String gas2 = "gas2";
        private String gas3 = "gas3";
        private String gas4 = "gas4";

        private String other0 = "other0";
        private String other1 = "other1";
        private String other2 = "other2";
        private String other3 = "other3";
        private String other4 = "other4";

        private BigDecimal originalValue = new BigDecimal("100.01");
        private BigDecimal assetValue0 = new BigDecimal("90.99");
        private BigDecimal assetValue1 = new BigDecimal("80.33");

        private String hasClaims = "有";
        private BigDecimal claims = new BigDecimal("100099.99");
        private String hasDebt = "有";
        private BigDecimal debt = new BigDecimal("2222299.02");

        private String hasProperty = "有";
        private String propertyName = "测试公司";
        private BigDecimal unitPrice = new BigDecimal("10.8");
        private String arrears = "否";
    }

    public HSSFWorkbook createCardExcel(List<AssetHouse> assetHouseList) {

        List<CardDataInfo> cardDataList = this.objectExchange(assetHouseList);
        HSSFWorkbook workbook = new HSSFWorkbook();
        cardDataList.forEach(cardData -> {

            Map<String, RowInfo> rowInfoMap = this.dataChange(cardData);

            Sheet sheet = workbook.createSheet(cardData.getSheetName());
            CellStyle[] cellStyles = {this.style0(workbook), this.style1(workbook), this.style2(workbook)};

            workbook.createCellStyle();

            Integer maxRowIndex = rowInfoMap.values().stream()
                    .mapToInt(rowInfo -> rowInfo.ord).max().getAsInt();
            Integer minRowIndex = rowInfoMap.values().stream()
                    .mapToInt(rowInfo -> rowInfo.ord).min().getAsInt();
            IntStream.rangeClosed(minRowIndex, maxRowIndex).forEach(i -> {
                Row row = sheet.createRow(i);

                row.setHeight((short) rowInfoMap.values().stream().filter(rowInfo -> rowInfo.ord == i)
                        .mapToInt(rowInfo -> rowInfo.height).max().getAsInt());
                List<String> keyList = rowInfoMap.entrySet().stream().filter(entry -> entry.getValue().ord == i)
                        .map(entry -> entry.getKey()).collect(Collectors.toList());
                keyList.forEach(key -> {
                    RowInfo rowInfo = rowInfoMap.get(key);
                    Cell cell = row.createCell(rowInfo.abs);
                    cell.setCellValue(rowInfo.value);
                    if (key.contains(KEY) && !rowInfo.special) {
                        cell.setCellStyle(cellStyles[2]);
                    } else {
                        cell.setCellStyle(cellStyles[rowInfo.special ? 0 : 1]);
                    }
                    sheet.setColumnWidth(rowInfo.abs, rowInfo.width);
                });
            });

            this.merge(sheet, rowInfoMap);
        });
        return workbook;
    }

    private List<CardDataInfo> objectExchange(List<AssetHouse> assetHouseList) {

        return assetHouseList.stream().map(assetHouse -> {
            if(Objects.isNull(assetHouse)) return null;
            CardDataInfo cardData = new CardDataInfo();
            if (Objects.isNull(assetHouse)) return cardData;
            cardData.sheetName = "hello-name-" + new Random().nextInt(200);
            cardData.name = "hello-name";
            cardData.address = "hello-address";
            cardData.landArea = new BigDecimal("100.00");
            cardData.buildArea = new BigDecimal("99.00");
            cardData.useType = "住宅";
            cardData.structure = "未知结构";
            cardData.totalFloor = 100; //总层数
            cardData.floor = 60;  //所在楼层
            cardData.ownerType = "产权";   //权属性质，产权/使用权
            cardData.warrantNum = "123456789";  //权证号/使用权证号
            cardData.ownerShip = "测试";
            cardData.areaType = "测试";
            cardData.areaCardNo = "123456789";
            cardData.takeOverTime = "2019-10-10";
            cardData.takeOverStatus = "闲置";
            cardData.apartmentType = "五室一厅一卫";
            cardData.ownerShipStatus = "良好";
            cardData.buildYear = "2008-09-08";
            cardData.houseHold = new Random().nextInt(100);
            cardData.transProDate = "2009-02-19";
            cardData.transOperateDate = "2010-11-02";
            return cardData;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }

    private Map<String, RowInfo> dataChange(CardDataInfo cardData) {
        Map<String, RowInfo> map = ROW_INFO_MAP.get();

        map.keySet().stream().filter(key -> !key.startsWith(KEY)).forEach(key -> {

            Object obj = null;
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(key, cardData.getClass());
                Method method = descriptor.getReadMethod();
                obj = method.invoke(cardData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.get(key).value = Objects.nonNull(obj) ? obj.toString() : "";
        });

        return map;
    }

    private void merge(Sheet sheet, Map<String, RowInfo> rowInfoMap) {
        rowInfoMap.values().forEach(rowInfo -> {
            MergeInfo mergeInfo = rowInfo.mergeInfo;
            if (Objects.nonNull(mergeInfo)) {
                CellRangeAddress rangeAddress = new CellRangeAddress(rowInfo.ord, rowInfo.ord + mergeInfo.down,
                        rowInfo.abs, rowInfo.abs + mergeInfo.right);
                sheet.addMergedRegion(rangeAddress);
            }
        });
    }

    private CellStyle style0(HSSFWorkbook workbook) {
        CellStyle cellStyle = this.style(workbook, (short) 330, true);

        return cellStyle;
    }

    private CellStyle style1(HSSFWorkbook workbook) {
        CellStyle cellStyle = this.style(workbook, (short) 250, false);

        return cellStyle;
    }

    private CellStyle style2(HSSFWorkbook workbook) {
        CellStyle cellStyle = this.style(workbook, (short) 250, true);

        return cellStyle;
    }

    private CellStyle style(HSSFWorkbook workbook, Short fontHeight, Boolean bold) {
        Font font = workbook.createFont();
        font.setFontHeight(fontHeight);
        font.setBold(bold);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);

        return cellStyle;
    }
}
