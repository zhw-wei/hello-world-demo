package com.hello.demo.snowFlake;

import org.junit.jupiter.api.Test;

/**
 * @author: zhaohw
 * @date: 2021.04.21 下午 3:01
 * @description:
 */
public class SnowFlakeTest {

    @Test
    public void testCreateId(){
        System.out.println(SnowFlakeUtil.createId());
    }}
