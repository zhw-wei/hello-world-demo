package com.hello.demo.cal;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class MonthCal {

    public static void month(Integer month){
        System.out.println("周一\t周二\t周三\t周四\t周五\t周六\t周日\t");

        int he = 6;
        int ln = 7;
        String[][] monthCal = new String[he][ln];

        Integer dayOfMonth = 1;
        Integer hmsn = 0;   //hour/minute/second/nanoOfSecond
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime time = ZonedDateTime.of(now.getYear(), month, dayOfMonth, hmsn, hmsn, hmsn, hmsn, ZoneId.systemDefault());

        //生成数组
        Integer currentMonth = month;
        while(currentMonth.equals(month)){
            int y = time.getDayOfWeek().getValue() - 1;//纵坐标
            for(int i=0; i<he; i++) {
                if (monthCal[i][y] == null) {
                    monthCal[i][y] = String.valueOf(time.getDayOfMonth());
                    if(time.getDayOfMonth()==1){//设置无日期的坐标
                        for(int m=0; m<y; m++){
                            monthCal[i][m] = "";
                        }
                    }
                    break;
                }
            }
            time = time.plusDays(1);
            currentMonth = time.getMonth().getValue();
        }

        //打印数组
        for(int i=0; i<he; i++){
            for(int j=0; j<ln; j++){
                String print = monthCal[i][j];
                if(Objects.isNull(print)) print = "";
                System.out.print(print + "\t\t");
            }
            System.out.println();
        }
    }
}
