package com.hello.demo.myexcel;

import com.alibaba.fastjson.JSON;
import com.hello.demo.util.ExcelUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zhaohw
 * @date: 2022.01.12 下午 6:37
 * 修改合同所属机构
 */
public class ReadExcel01 {

    private static final int USER_NAME_COL = 10;
    private static final int ORGAN_NAME_COL = 0;
    private static final int CONTRACT_CODE_COL = 2;

    //读取excel并生成update的sql语句
    public static void main(String[] args) throws Exception {

        //解析文件放在桌面
        String fileName = "20220322广州地区合同台账-刷状态";
        String path = String.format("c:/Users/user/Desktop/%s.xlsx", fileName);

        main0(path);
//        main1(path);
    }

    private static void main0(String path) throws Exception {
        //解析结果
        Map<String, List<String>> contractMap = analysis(path);

        Map<String, Integer> organIdMap = new HashMap<>();
//        printInOrgan(contractMap);

        organIdMapInit(organIdMap);

        if (organIdMap.isEmpty()) return;

        List<String> errorList = new ArrayList<>();
        List<String> nullVal = new ArrayList<>();
        contractMap.forEach((organName, codeList) -> {
            String temp = "-- %s \nupdate ctt_contract set organ_id=%s where contract_code in (%s);";
            Integer organId = organIdMap.get(organName);
            String codeStr = codeList.stream().map(code -> String.format("'%s'", code))
                    .collect(Collectors.joining(","));
            String format = String.format(temp, organName, organId, codeStr);
            if (Objects.nonNull(organId)) {
                //输出update sql语句
                System.out.println(format);
            } else {
                errorList.add(format);
            }

            if (Objects.isNull(organId)) nullVal.add(organName);
        });
        System.out.println();
        //输出错误的update sql语句
        if (!errorList.isEmpty()) {
            System.out.println("-- 无法匹配机构的数据");
            for (String s : errorList) {
                System.out.println(s);
            }
        }

        System.out.println();

        //输出未查询到的机构名称
        String temp = "select * from copm_b.organ where status=1 and name in (%s);";
        System.out.println(String.format(temp, nullVal.stream().map(name -> String.format("'%s'", name))
                .collect(Collectors.joining(","))));
    }

    private static void main1(String path) throws Exception {

        Map<String, List<String>> map0 = analysis2(path);

//        printIdUser(map0);

        //根据用户名查询用户id
        Map<String, Integer> userNameIdMap = new HashMap<>();
        userIdMapInit(userNameIdMap);

        List<String> errorList = new ArrayList<>();
        List<String> nullVal = new ArrayList<>();
        map0.forEach((userName, codeList) -> {
            String temp = "-- %s \nupdate ctt_contract set create_user_id=%s, create_user_name='%s', user_id=%s, user_name='%s' where contract_code in (%s);";
            Integer userId = userNameIdMap.get(userName);
            String codeStr = codeList.stream().map(code -> String.format("'%s'", code))
                    .collect(Collectors.joining(","));
            String format = String.format(temp, userName, userId, userName, userId, userName, codeStr);
            if (Objects.nonNull(userId)) {
                //输出update sql语句
                System.out.println(format);
            } else {
                errorList.add(format);
            }

            if (Objects.isNull(userId)) nullVal.add(userName);
        });
        System.out.println();
        //输出错误的update sql语句
        if (!errorList.isEmpty()) {
            System.out.println("-- 无法匹配用户的数据");
            for (String s : errorList) {
                System.out.println(s);
            }
        }

        System.out.println();

        //输出未查询到的机构名称
        String temp = "select * from copm_b.tb_uhome_admin_user where status=1 and name in (%s);";
        System.out.println(String.format(temp, nullVal.stream().map(name -> String.format("'%s'", name))
                .collect(Collectors.joining(","))));
    }

    private static Map<String, List<String>> analysis(String path) throws Exception {
        System.out.println("start analysis ...");
        Workbook workbook = WorkbookFactory.create(new File(path));

        System.out.println("start read ...");
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, List<String>> map0 = new HashMap<>();

        for (int i = 1; i < sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            //机构名称
            String organName = ExcelUtil.readAsString(ORGAN_NAME_COL, row);
            //合同编码
            String contractCode = ExcelUtil.readAsString(CONTRACT_CODE_COL, row);

            if (organName.isEmpty() || contractCode.isEmpty()) continue;

            List<String> codeList = map0.getOrDefault(organName, new ArrayList<>());
            codeList.add(contractCode);
            map0.put(organName, codeList);
        }

        System.out.println(JSON.toJSONString(map0));

        workbook.close();
        return map0;
    }

    private static Map<String, List<String>> analysis2(String path) throws Exception {

        System.out.println("start analysis 2 ...");
        Workbook workbook = WorkbookFactory.create(new File(path));
        System.out.println("start read 2 ...");
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, List<String>> map0 = new HashMap<>();

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            //创建人
            String userName = ExcelUtil.readAsString(USER_NAME_COL, row);
            String contractCode = ExcelUtil.readAsString(CONTRACT_CODE_COL, row);

            if (userName.isEmpty() || contractCode.isEmpty()) continue;

            List<String> codeList = map0.getOrDefault(userName, new ArrayList<>());
            codeList.add(contractCode);
            map0.put(userName, codeList);
        }
        System.out.println(JSON.toJSONString(map0));

        workbook.close();
        return map0;
    }

    private static void printInOrgan(Map<String, List<String>> map) {
        List<List<String>> list = new ArrayList<>();
        int i = 0;
        List<String> list0 = new ArrayList<>();
        for (String s : map.keySet()) {
            list0.add(s);
            if (i++ >= 30) {
                list.add(list0);
                list0 = new ArrayList<>();
                i = 0;
            }
        }
        list.add(list0);

        for (List<String> strings : list) {
            System.out.println(strings.stream().map(str -> String.format("'%s'", str))
                    .collect(Collectors.joining(",", "select concat('map.put(\"', name, '\",', organ_id, ');') from copm_b.organ where name in(",
                            ") and orgtype=6 and status=1 order by name;")));
            System.out.println();
        }
    }

    private static void printIdUser(Map<String, List<String>> map) {
        List<List<String>> list = new ArrayList<>();
        int i = 0;
        List<String> list0 = new ArrayList<>();
        for (String s : map.keySet()) {
            list0.add(s);
            if (i++ >= 1000) {
                list.add(list0);
                list0 = new ArrayList<>();
                i = 0;
            }
        }
        list.add(list0);

        for (List<String> strings : list) {
            System.out.println(strings.stream().map(str -> String.format("'%s'", str))
                    .collect(Collectors.joining(",",
                            "select concat('map.put(\"', NAME, '\",', USER_ID, ');') from copm_b.tb_uhome_admin_user where name in(",
                            ") and status=1 order by name;")));
            System.out.println();
        }
    }

    private static void userIdMapInit(Map<String, Integer> map) {

    }

    private static void organIdMapInit(Map<String, Integer> map) {

    }
}
