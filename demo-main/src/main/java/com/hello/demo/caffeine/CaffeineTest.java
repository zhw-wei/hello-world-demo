package com.hello.demo.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * https://zhuanlan.zhihu.com/p/329684099
 * 多级缓存是可以使用此框架作为内存缓存
 * @author zhw
 * @date 2022/5/23 21:53
 */
public class CaffeineTest {

    @Test
    public void test01(){
        Cache<String, Integer> cache = Caffeine.newBuilder()
                .maximumSize(1000)//最大key数量
                .recordStats()
                .expireAfterWrite(5, TimeUnit.MINUTES)//表示上次创建或更新超过一定时间后过期
                .expireAfterAccess(2, TimeUnit.MINUTES)//表示上次读写超过一定时间后过期
                .build();

        cache.put("hello01", 1);

        System.out.println(cache.getIfPresent("hello01"));
        System.out.println(cache.getIfPresent("hello02"));

    }
}
