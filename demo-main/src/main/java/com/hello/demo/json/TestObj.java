package com.hello.demo.json;

import lombok.Data;

import java.util.List;

@Data
public class TestObj {

    private int id;
    private String name;
    private int age;
    private List<ObjInfo> dataList;

    @Data
    public static class ObjInfo{
        private int id;
        private String name;
        private int age;
    }
}
