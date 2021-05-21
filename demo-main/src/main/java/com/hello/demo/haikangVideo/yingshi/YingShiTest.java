package com.hello.demo.haikangVideo.yingshi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaohw
 * @date: 2021.05.21 上午 11:47
 */
public class YingShiTest {

    @Test
    public void test01(){
        List<TestDto> list = new ArrayList<>();
        list.add(new TestDto(1, "01"));

        TestDto dto = new TestDto(1, "01");

        System.out.println(list.stream().anyMatch(var0 -> !var0.getId().equals(dto.getId())));
    }

    @Data
    @AllArgsConstructor
    class TestDto{
        private Integer id;
        private String name;
    }
}
