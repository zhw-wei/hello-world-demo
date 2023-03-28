package com.hello.demo.algorithms.hwTest;

import java.util.*;
import java.util.regex.Pattern;

public class HwTest01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        String[] arr = string.split(";");

        int x = 0;
        int y = 0;

        //A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动
        Pattern pattern = Pattern.compile("^[ADWS][0-9]+$");

        for(int i=0; i<arr.length; i++){
            //过滤无效的字符串
            String str = arr[i].trim();
            if(!pattern.matcher(str).matches()) continue;

            char c = str.charAt(0);
            int num = Integer.parseInt(str.substring(1));

            //坐标移动
            switch (c){
                case 'A': x = x - num; break;
                case 'D': x = x + num; break;
                case 'W': y = y + num; break;
                case 'S': y = y - num; break;
            }
        }

        System.out.println(x + "," + y);
    }


}
