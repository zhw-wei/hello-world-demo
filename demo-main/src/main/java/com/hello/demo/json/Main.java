package com.hello.demo.json;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        String str = "{\"id\":1,\"name\":\"123\",\"age\":11,\"hello\":\"111\",\"dataList\":[{\"id\":10,\"name\":\"222\",\"age\":222}]}";
        String str2 = "{}";

        Optional<TestObj> testObj = ObjectMapperUtil.string2Object(str2, TestObj.class);

        System.out.println(testObj.orElse(new TestObj()));

        System.out.println(ObjectMapperUtil.object2String(testObj.orElse(new TestObj())).orElse(null));
        System.out.println(ObjectMapperUtil.object2String(new TestObj()));


    }
}
