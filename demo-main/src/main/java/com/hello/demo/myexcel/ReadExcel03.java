package com.hello.demo.myexcel;

import com.alibaba.fastjson.JSON;
import com.hello.demo.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 核算会计科目导入sql生成
 *
 * @author: zhaohw
 * @date: 2022.03.09 下午 5:25
 */
public class ReadExcel03 {


    //科目属性(1:现金科目，2:银行科目，3:现金等价物)

    @Test
    public void test() throws Exception {
        String path = "";
        String json = "";
        List<ExcelInfo> excelInfoList;
        if (json.isEmpty()) {
            excelInfoList = this.readExcel(path);
            System.out.println(JSON.toJSONString(excelInfoList));
        } else {
            excelInfoList = JSON.parseArray(json, ExcelInfo.class);
        }
        if (excelInfoList.isEmpty()) {
            System.out.println("excelInfo is size is 0");
            return;
        }

        Map<String, Integer> organMap = new HashMap<>();
        Map<String, Integer> subjectMap = new HashMap<>();
        Map<String, Integer> extTypeMap = new HashMap<>();

        String sqlTemp0 = "select organ_id, name, " +
                "concat('map.put(\"', name, '\",', organ_id, ');')" +
                "from organ where name in (%s);";
        System.out.println("-- 查询所有一级机构");
        System.out.println(String.format(sqlTemp0,
                excelInfoList.stream().map(info -> info.topOrganName).distinct()
                        .map(name -> String.format("'%s'", name))
                        .collect(Collectors.joining(",")))
        );
        this.initOrganMap(organMap);


        System.out.println("-- 查询所有科目表名称");
        String sqlTemp1 = "select subject_id, subject_name, organ_id, " +
                "concat('map.put(\"', subject_name, '\",', subject_id, ');')" +
                "from hs_subject where status=1 and subject_name in (%s);";
        System.out.println(String.format(sqlTemp1,
                excelInfoList.stream().map(info -> info.subjectName).distinct()
                        .map(name -> String.format("'%s'", name))
                        .collect(Collectors.joining(",")))
        );
        this.initSubjectMap(subjectMap);

        System.out.println("-- 查询所有辅助核算类型");
        String sqlTemp2 = "select ext_id, ext_name, organ_id" +
                "concat('map.put(\"', ext_name, '\",', ext_id, ');')" +
                " from hs_ext_type where status=1 and ext_name in (%s);";
        System.out.println(String.format(sqlTemp2,
                excelInfoList.stream().map(info -> info.extNameList).flatMap(list -> list.stream()).distinct()
                        .map(name -> String.format("'%s'", name))
                        .collect(Collectors.joining(",")))
        );
        this.initExtTypeMap(extTypeMap);

        //初始化会计科目主键
        int subItemId = 1;
        for (ExcelInfo info : excelInfoList) {
            info.subItemId = subItemId++;
        }

        String sqlTemp3 = "insert into hs_sub_item " +
                "(sub_item_id,subject_id,sub_item_code,sub_item_name,sub_item_type," +
                "status,create_user,create_date,update_user,update_date) " +
                "values %s;";
        String valueTemp0 = "\n(%s, %s, %s, %s, %s, 1, -1, '创建人', -1, '修改人')";
        String values0 = excelInfoList.stream().map(info -> String.format(valueTemp0, info.subItemId,
                organMap.get(info.topOrganName), subjectMap.get(info.subjectName), info.subItemCode, info.subItemName))
                .collect(Collectors.joining(","));
        System.out.println("-- 会计科目入库, total: " + excelInfoList.size());
        System.out.println(String.format(sqlTemp3, values0));

        String sqlTemp4 = "insert into hs_acct_ext_type_rel " +
                "(rel_id,sub_item_id,ext_id) values %s;";
        int relId = 1;
        String valueTemp1 = "\n (%s, %s, %s)";
        List<String> values1 = new ArrayList<>();
        for (ExcelInfo info : excelInfoList) {
            for (String extName : info.extNameList) {
                values1.add(String.format(valueTemp1, relId++, info.subItemId, extTypeMap.get(extName)));
            }
        }
        System.out.println("-- 会计科目与辅助核算类型关系，total: " + values1.size());
        System.out.println(String.format(sqlTemp4, values1.stream().collect(Collectors.joining(","))));
    }

    //解析excel
    private List<ExcelInfo> readExcel(String path) throws Exception {
        Workbook workbook = WorkbookFactory.create(new File(path));
        Sheet sheet = workbook.getSheetAt(0);

        List<ExcelInfo> excelInfoList = new ArrayList<>();

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            ExcelInfo excelInfo = new ExcelInfo();
            excelInfo.topOrganName = ExcelUtil.readAsString(0, row);
            excelInfo.subjectName = ExcelUtil.readAsString(1, row);
            excelInfo.subItemCode = ExcelUtil.readAsString(2, row);
            excelInfo.subItemName = ExcelUtil.readAsString(3, row);
            excelInfo.extNameList = Stream.of(ExcelUtil.readAsString(4, row).split(","))
                    .map(String::trim).collect(Collectors.toList());
            excelInfo.subItemType = ExcelUtil.readAsString(5, row);

            excelInfoList.add(excelInfo);
        }
        return excelInfoList;
    }


    class ExcelInfo {
        Integer subItemId;
        String topOrganName;    //一级机构名称
        String subjectName;     //科目表名称
        String subItemCode;     //会计科目编码
        String subItemName;     //会计科目名称
        List<String> extNameList;   //辅助核算类型
        String subItemType;     //科目属性名称
    }

    private void initOrganMap(Map<String, Integer> organMap) {

    }

    private void initSubjectMap(Map<String, Integer> subjectMap) {

    }

    private void initExtTypeMap(Map<String, Integer> extTypeMap) {

    }

}
