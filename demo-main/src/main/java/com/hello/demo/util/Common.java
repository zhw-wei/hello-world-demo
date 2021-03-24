package com.hello.demo.util;

import java.io.File;
import java.util.Random;
import java.util.Set;

public class Common {
    public static String sheetName(Set<String> sheetNameSet, String sheetName) {
        Boolean contains = sheetNameSet.contains(sheetName);
        if (!contains) {
            sheetNameSet.add(sheetName);
            return sheetName;
        } else {
            Random random = new Random();
            String sheetNameNew = sheetName + random.nextInt(100);
            Integer range = 10;
            while (sheetNameSet.contains(sheetNameNew) && range > 0) {
                sheetNameNew = sheetName + random.nextInt(100);
                range--;
            }
            sheetNameSet.add(sheetNameNew);
            return sheetNameNew;
        }
    }

    public static File filePathExcel(){
        String filePath_t = "D:/test/demo/%s.xls";
        String filePath_m = "/Users/zhw/test/%s.xls";
        Boolean MAC = System.getProperties().get("os.name").toString().toLowerCase().contains("mac");

        String filePath = String.format(MAC ? filePath_m : filePath_t, SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return file;
    }

    public static File filePathPdf(){
        String filePath_t = "D:/test/demo/%s.pdf";
        String filePath_m = "/Users/zhw/test/%s.pdf";
        Boolean MAC = System.getProperties().get("os.name").toString().toLowerCase().contains("mac");
        String filePath = String.format(MAC ? filePath_m : filePath_t, SecurityUtils.createNickName());
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return file;
    }
}
