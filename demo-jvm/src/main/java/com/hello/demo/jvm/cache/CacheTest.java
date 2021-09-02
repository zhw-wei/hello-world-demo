package com.hello.demo.jvm.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author: zhaohw
 * @date: 2021.09.02 上午 10:11
 */
@DisplayName("自定义缓存测试")
public class CacheTest {

    public MapCache<String, String> CACHE;

    public MapCache<String, TestDto> CACHE_2;

    @BeforeEach
    public void before(){
        this.CACHE = new WeakMapCache<>(new CacheRule(5));
        this.CACHE.put("test01", "test01_value");
        this.CACHE.put("test02", "test02_value");
        this.CACHE.put("test03", "test03_value");

        this.CACHE_2 = new WeakMapCache<>(new CacheRule(5));
        this.CACHE_2.put("test01", new TestDto(1, "test01"));
        this.CACHE_2.put("test02", new TestDto(2, "test02"));
        this.CACHE_2.put("test03", new TestDto(3, "test03"));
    }

    @Test
    @DisplayName("测试重新执行put方法是否会覆盖_string")
    public void test0(){
        this.CACHE.put("test01", "test01_value_2");
        String test01 = this.CACHE.get("test01");
        Assertions.assertTrue("test01_value_2".equals(test01));
    }

    @Test
    @DisplayName("测试重新执行put方法是否会覆盖_object")
    public void test01(){

        this.CACHE_2.put("test01", new TestDto(1, "test01_2"));
        TestDto test011 = this.CACHE_2.get("test01");
        Assertions.assertTrue(Objects.nonNull(test011) && test011.getName().equals("test01_2"));
    }

    @Test
    @DisplayName("测试自动过期_string")
    public void test1() throws InterruptedException {
        String test01 = this.CACHE.get("test01");
        Assertions.assertTrue("test01_value".equals(test01));

        Thread.sleep(5 * 1000);

        String test011 = this.CACHE.get("test01");
        Assertions.assertTrue(Objects.isNull(test011));
    }

    @Test
    @DisplayName("测试自动过期_object")
    public void test11() throws InterruptedException {
        TestDto test01 = this.CACHE_2.get("test01");
        Assertions.assertTrue(Objects.nonNull(test01) && test01.getName().equals("test01"));

        Thread.sleep(5 * 1000);

        test01 = this.CACHE_2.get("test02");
        Assertions.assertTrue(Objects.isNull(test01));
    }
}

@Data
@AllArgsConstructor
class TestDto{
    private Integer id;
    private String name;
}