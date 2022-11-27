package com.hello.demo.algorithms.hw;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class HWTest01 {

    public static void main(String[] args) {

        test02();
    }

    public static void test01() {

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){// 多个输入使用循环
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(a+b);
        }
    }
     public static void test02(){
         Scanner sc = new Scanner(System.in);

         // 输入要写入的字符串数量
         int n = sc.nextInt();
         System.out.println(n);
         List<String> strList = new ArrayList<>();
         for(int i=0; i< n; i++){
             strList.add(sc.next());
         }

         int result = 0;
         for (String str : strList) {
             result += changeMoney(str);
         }
         System.out.println(result);
     }

     // 货币转换
     public static int changeMoney(String str){
        str = str.toLowerCase();
        int cny=100, fen=cny*100;
        int jpy=1825, sen=jpy*100;
        int hkd=123, cents= hkd*100;
        int eur=14, eurocents=eur*100;
        int gbp=12, pence=gbp*100;

        List<String> list =new ArrayList<>();
        int start = 0;
        for(int index=0; index<str.length(); index++){
            // 截取字符串 123cny456fen
            if(index==str.length()-1){
                list.add(str.substring(start, index+1));
            }else{
                char c = str.charAt(index);
                char c1 = str.charAt(index + 1);

                if(c>='a' && c<='z' && c1>='0' && c1<='9'){
                    list.add(str.substring(start, index+1));
                    start = index + 1;
                }
            }
        }

        BigDecimal result = BigDecimal.ZERO;
         for (String money : list) {

             String s = "";
             int v = 1;
             if(money.contains("cny")){
                 s = "cny";
                 v = cny;
             }else if(money.contains("fen")){
                 s = "fen";
                 v = fen;
             }else if(money.contains("jpy")){
                 s = "jpy";
                 v = jpy;
             }else if(money.contains("sen")){
                 s = "sen";
                 v = sen;
             }else if(money.contains("hkd")){
                 s = "hkd";
                 v = hkd;
             }else if(money.contains("cents")){
                 s = "cents";
                 v = cents;
             }else if(money.contains("euro")){
                 s = "euro";
                 v = eurocents;
             }else if(money.contains("eur")){
                 s = "eur";
                 v = eur;
             }else if(money.contains("gbp")){
                 s = "gbp";
                 v = gbp;
             }else if(money.contains("pence")){
                 s = "pence";
                 v = pence;
             }

             BigDecimal value = BigDecimal.valueOf(Integer.parseInt(money.replaceAll(s, "")));
             result = result.add(value.multiply(new BigDecimal(10000)).divide(new BigDecimal(v), 10, RoundingMode.HALF_DOWN));
         }

        return result.intValue();
     }

     @Test
     public void testCNY(){
         System.out.println(changeMoney("1825jpy"));
     }
}
