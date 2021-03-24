package com.hello.demo.myexcel.excelv3;

import com.hello.demo.myexcel.excelv3.excelInfo.ExcelInfoObject01;
import com.hello.demo.json.ObjectMapperUtil;

import java.io.*;

public class Main {
    private static final String filePath = "/Users/zhw/test/Way_4c3abf63849b.xls";
    private static final String filePath_2 = "D:\\test\\demo\\hello.xlsx";

    private static final Boolean MAC = System.getProperties().get("os.name").toString().toLowerCase().contains("mac");
    public static void main(String[] args) throws Exception {

        InputStream inputStream = new FileInputStream(new File(MAC ? filePath : filePath_2));
        ExcelExplain.ExplainResult<ExcelInfoObject01> explainResult =
                new ExcelExplain<>(inputStream, ExcelInfoObject01.class).explain(2, 4);

        System.out.println(explainResult.getErrorMsg());
        System.out.println("===================");
        System.out.println(explainResult.getResultList());
        System.out.println("===================");
        System.out.println(ObjectMapperUtil.object2String(explainResult.getResultList()).orElse("无"));
        System.out.println("===================");
        System.out.println(explainResult.getResultList().size());
    }
}
