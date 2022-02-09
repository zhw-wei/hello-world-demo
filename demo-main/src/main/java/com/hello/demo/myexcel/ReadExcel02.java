package com.hello.demo.myexcel;

import com.alibaba.fastjson.JSON;
import com.hello.demo.myexcel.excelv2.CreateExcelV2;
import com.hello.demo.myexcel.excelv2.ExcelInfo;
import com.hello.demo.util.ExcelUtil;
import com.hello.demo.util.SecurityUtils;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: zhaohw
 * @date: 2022.02.09 下午 2:49
 */
public class ReadExcel02 {
    static int ATTR_START = 201;
    static int ATTR_END = 227;

    public static void main(String[] args) throws Exception {
        List<ContractExcelInfo> contractList = read_total();
        System.out.println("原始值： " + contractList);

        List<UserInfo> userList = read_write_user();
        Map<String, UserInfo> userMap = userList.stream().collect(Collectors.toMap(var0 -> var0.getCttId(),
                Function.identity(), (var1, var2) -> var1));
        System.out.println("相对方: " + userList);

        for (ContractExcelInfo info : contractList) {
            UserInfo userInfo = userMap.getOrDefault(info.getVal0(), new UserInfo());
            info.setVal100(userInfo.getUserTypeName());
            info.setVal101(userInfo.getUserTypeName2());
        }

        List<ContractExcelInfo> attrList = read_attr_info();
        Map<String, ContractExcelInfo> attrMap = attrList.stream().collect(Collectors.toMap(var0 -> var0.getVal0(),
                Function.identity(), (var1, var2) -> var1));
        System.out.println("属性值： " + attrList);

        for (ContractExcelInfo info : contractList) {
            ContractExcelInfo attrInfo = attrMap.get(info.getVal0());
            if (Objects.nonNull(attrInfo)) {
                //22~39
                for (int i = ATTR_START; i <= ATTR_END; i++) {
                    String param = String.format("val%s", i);
                    PropertyDescriptor descriptor = new PropertyDescriptor(param, ContractExcelInfo.class);
                    String value = String.valueOf(descriptor.getReadMethod().invoke(attrInfo));
                    descriptor.getWriteMethod().invoke(info, value);
                }
            }
        }

        for (Field field : ContractExcelInfo.class.getDeclaredFields()) {
            String fieldName = field.getName();
            PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, ContractExcelInfo.class);
            Method readMethod = descriptor.getReadMethod();
            Method writeMethod = descriptor.getWriteMethod();

            for (ContractExcelInfo info : contractList) {
                String value = String.valueOf(readMethod.invoke(info));
                if(Objects.isNull(value) || "null".equalsIgnoreCase(value)){
                    writeMethod.invoke(info, "");
                }
            }
        }

        System.out.println("解析结果： " + JSON.toJSONString(contractList));

        CreateExcelV2 createExcel = new CreateExcelV2(contractList, "hello_world", false);

        createExcel.createExcel();

        String filePath = String.format("D:/test/demo/%s.xls", SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        createExcel.createExcel().write(file);
    }

    private static List<ContractExcelInfo> read_total() throws Exception {
        String path = "D:/document/2022-02/0209_05_2.xlsx";
        Workbook workbook = WorkbookFactory.create(new File(path));

        Sheet sheet = workbook.getSheetAt(0);
        List<ContractExcelInfo> list = new ArrayList<>();

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            ContractExcelInfo excelInfo = new ContractExcelInfo();

            String param = "val%s";
            for (int j = 0; j <= 19; j++) {
                String totalParam = String.format(param, j);
                String value = ExcelUtil.readAsString(j, row);

                PropertyDescriptor descriptor = new PropertyDescriptor(totalParam, ContractExcelInfo.class);
                Method writeMethod = descriptor.getWriteMethod();
                writeMethod.invoke(excelInfo, value);

            }
            excelInfo.setVal0(String.valueOf(Double.valueOf(excelInfo.getVal0()).intValue()));

            list.add(excelInfo);
        }
        workbook.close();
        return list;

    }


    private static List<UserInfo> read_write_user() throws Exception {
        String path = "D:/document/2022-02/0209_02_2.xlsx";
        Workbook workbook = WorkbookFactory.create(new File(path));

        Sheet sheet = workbook.getSheetAt(0);
        List<UserInfo> list = new ArrayList<>();

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            double cttId = row.getCell(1).getNumericCellValue();
            //甲方、乙方
            String userTypeInfo = ExcelUtil.readAsString(3, row);
            //甲方名称、乙方名称
            String userTypeName = ExcelUtil.readAsString(4, row);

            UserInfo userInfo = new UserInfo();
            userInfo.setCttId(String.valueOf(Double.valueOf(cttId).intValue()));
            userInfo.setUserType(userTypeInfo);
            userInfo.setUserTypeName(userTypeName);
            list.add(userInfo);
        }

        List<UserInfo> list2 = new ArrayList<>();
        list.stream().collect(Collectors.groupingBy(var0 -> var0.getCttId()))
                .forEach((parId, list0) -> {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setCttId(parId);

                    for (UserInfo info : list0) {

                        if ("甲方".equals(info.getUserType())) {
                            userInfo.setUserTypeName(info.getUserTypeName());
                        } else {
                            userInfo.setUserTypeName2(info.getUserTypeName());
                        }
                    }
                    list2.add(userInfo);
                });
        list2.sort(Comparator.comparing(UserInfo::getCttId));
        workbook.close();
        return list2;
    }

    private static List<ContractExcelInfo> read_attr_info() throws Exception {

        Map<String, Method> methodMap = new HashMap<>();
        for (int i = ATTR_START; i <= ATTR_END; i++) {
            String param = String.format("val%s", i);
            PropertyDescriptor descriptor = new PropertyDescriptor(param, ContractExcelInfo.class);
            Method writeMethod = descriptor.getWriteMethod();
            String headerName = ContractExcelInfo.class.getDeclaredField(param)
                    .getDeclaredAnnotation(ExcelInfo.class).headerName();
            methodMap.put(headerName, writeMethod);
        }

        String path = "D:/document/2022-02/0209_04_2.xlsx";
        Workbook workbook = WorkbookFactory.create(new File(path));

        Sheet sheet = workbook.getSheetAt(0);

        Map<String, ContractExcelInfo> attrMap = new HashMap<>();
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            String cttId = String.valueOf(Double.valueOf(ExcelUtil.readAsString(1, row)).intValue());
            String attrName = ExcelUtil.readAsString(4, row);
            String attrValue = ExcelUtil.readAsString(6, row);
            String attrSelectValue = ExcelUtil.readAsString(7, row);

            ContractExcelInfo info = attrMap.getOrDefault(cttId, new ContractExcelInfo());
            info.setVal0(cttId);
            if (methodMap.containsKey(attrName)) {
                methodMap.get(attrName).invoke(info, "NULL".equalsIgnoreCase(attrSelectValue) ? attrValue : attrSelectValue);
            }
            attrMap.put(cttId, info);
        }
        return attrMap.values().stream().collect(Collectors.toList());
    }

    @Data
    public static class UserInfo {
        //合同主键
        private String cttId;

        //类型：甲方、乙方
        private String userType;

        //甲方名称
        private String userTypeName = "";

        //乙方名称
        private String userTypeName2 = "";
    }

    /*
    主键	机构名称	合同名称	合同编码	合同分类	合同状态	变更状态	签约状态	合同来源	是否框架协议
    合同签约时间	合同生效开始时间	合同生效结束时间	是否用章	用章类型	归档状态	合同创建人
    合同创建时间	申请时间	审批完成时间
     */
    @Data
    public static class ContractExcelInfo {
        @ExcelInfo(headerName = "合同主键")
        private String val0;
        @ExcelInfo(headerName = "机构名称")
        private String val1;
        @ExcelInfo(headerName = "合同名称")
        private String val2;
        @ExcelInfo(headerName = "合同编码")
        private String val3;
        @ExcelInfo(headerName = "合同分类")
        private String val4;
        @ExcelInfo(headerName = "合同状态")
        private String val5;
        @ExcelInfo(headerName = "变更状态")
        private String val6;
        @ExcelInfo(headerName = "签约状态")
        private String val7;
        @ExcelInfo(headerName = "合同来源")
        private String val8;
        @ExcelInfo(headerName = "是否框架协议")
        private String val9;
        @ExcelInfo(headerName = "合同签约时间")
        private String val10;
        @ExcelInfo(headerName = "合同生效开始时间")
        private String val11;
        @ExcelInfo(headerName = "合同生效结束时间")
        private String val12;
        @ExcelInfo(headerName = "是否用章")
        private String val13;
        @ExcelInfo(headerName = "用章类型")
        private String val14;
        @ExcelInfo(headerName = "归档状态")
        private String val15;
        @ExcelInfo(headerName = "合同创建人")
        private String val16;
        @ExcelInfo(headerName = "合同创建时间")
        private String val17;
        @ExcelInfo(headerName = "申请时间")
        private String val18;
        @ExcelInfo(headerName = "审批完成时间")
        private String val19;
        @ExcelInfo(headerName = "合同金额")
        private String val20;

        @ExcelInfo(headerName = "甲方")
        private String val100;
        @ExcelInfo(headerName = "乙方")
        private String val101;

        @ExcelInfo(headerName = "增值-签约选项")
        private String val201;
        @ExcelInfo(headerName = "增值-业务类型")
        private String val202;
        @ExcelInfo(headerName = "增值-是否关联交易方")
        private String val203;
        @ExcelInfo(headerName = "增值-付款方式")
        private String val204;
        @ExcelInfo(headerName = "增值-是否合同范本")
        private String val205;
        @ExcelInfo(headerName = "增值-是否已提示付款")
        private String val206;
        @ExcelInfo(headerName = "增值-合同状态")
        private String val207;
        @ExcelInfo(headerName = "增值-合同类型")
        private String val208;
        @ExcelInfo(headerName = "增值-所属条线")
        private String val209;
        @ExcelInfo(headerName = "增值-履约保证金")
        private String val210;
        @ExcelInfo(headerName = "增值-合同金额")
        private String val211;
        @ExcelInfo(headerName = "增值-合同期限（月）")
        private String val212;
        @ExcelInfo(headerName = "增值-月租金/月合同额（元）")
        private String val213;
        @ExcelInfo(headerName = "增值-城市")
        private String val214;
        @ExcelInfo(headerName = "增值-合作单位公司名称")
        private String val215;
        @ExcelInfo(headerName = "增值-项目名称")
        private String val216;
        @ExcelInfo(headerName = "增值-合同简要信息")
        private String val217;
        @ExcelInfo(headerName = "增值-本合同金额")
        private String val218;
        @ExcelInfo(headerName = "增值-公司地址")
        private String val219;
        @ExcelInfo(headerName = "增值-使用途径")
        private String val220;
        @ExcelInfo(headerName = "增值-物业资源编号")
        private String val221;
        @ExcelInfo(headerName = "城市")
        private String val222;
        @ExcelInfo(headerName = "递增值")
        private String val223;
        @ExcelInfo(headerName = "合同金额（元）")
        private String val224;
        @ExcelInfo(headerName = "合同期限（月）")
        private String val225;
        @ExcelInfo(headerName = "合同文本")
        private String val226;
        @ExcelInfo(headerName = "是否框架合同")
        private String val227;
    }
}
