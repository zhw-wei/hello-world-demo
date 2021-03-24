package com.hello.demo.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ObjectMapperUtil {

    private static ObjectMapper objectMapper;

    static{
        objectMapper = new ObjectMapper();
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //属性为null的转换
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <T> Optional<T> string2Object(String str, Class<T> tClass){
        if(Objects.isNull(str) || str.trim().equals("")) return Optional.empty();

        try {
            return Optional.of(objectMapper.readValue(str, tClass));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static <T> Optional<String> object2String(T object){
        if(Objects.isNull(object)) return Optional.empty();

        try {
            return Optional.of(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
