package com.hello.demo.algorithms.hw;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HWTest02 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String v0 = sc.nextLine();
        String v1 = sc.nextLine();

        String[] s = v0.split(" ");
        String[] s1 = v1.split(" ");

        int wight = Integer.parseInt(s[0]);
        int num = Integer.parseInt(s[0]);

        // 6,5
        // 1,2,3,4,5
        int [] arr = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            arr[i] = Integer.parseInt(s1[i]);
        }

        int result = 0;
        Arrays.sort(arr);
        for (int index = 0; index < arr.length; index++) {
            if(arr[index]<=0) continue;
            if(index == arr.length-1){
                result++;
                break;
            }

            result ++;
            int value = wight - arr[index];
            if(arr[index+1]<=value) arr[index+1]*=-1;
        }
        System.out.println(result);
    }
}
