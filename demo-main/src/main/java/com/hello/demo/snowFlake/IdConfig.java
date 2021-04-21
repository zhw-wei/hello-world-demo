package com.hello.demo.snowFlake;

import java.util.function.Supplier;

/**
 * @author: zhaohw
 * @date: 2021.04.21 下午 3:06
 * @description:
 */
public interface IdConfig {

    Supplier<String> AID = () -> String.format("AID%s", SnowFlakeUtil.createId());
    Supplier<String> BID = () -> String.format("BID%s", SnowFlakeUtil.createId());
    Supplier<String> CID = () -> String.format("CID%s", SnowFlakeUtil.createId());
    Supplier<String> DID = () -> String.format("DID%s", SnowFlakeUtil.createId());
    Supplier<String> EID = () -> String.format("EID%s", SnowFlakeUtil.createId());
}
