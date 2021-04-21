package com.hello.demo.snowFlake;

import java.util.UUID;

/**
 * @author: zhaohw
 * @date: 2021.04.21 下午 3:01
 * @description:
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("====================");
        System.out.println("snowflake length: " + IdConfig.AID.get().length());
        System.out.println("uuid length: " + UUID.randomUUID().toString().length());

        System.out.println("====================");
        System.out.println(IdConfig.AID.get());
        System.out.println(IdConfig.BID.get());
        System.out.println(IdConfig.CID.get());
        System.out.println(IdConfig.DID.get());
        System.out.println(IdConfig.EID.get());
    }
}
