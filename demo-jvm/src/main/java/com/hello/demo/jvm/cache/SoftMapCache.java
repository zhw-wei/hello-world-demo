package com.hello.demo.jvm.cache;

import java.lang.ref.SoftReference;
import java.util.Objects;

/**
 * @author: zhaohw
 * @date: 2021.09.01 下午 4:56
 */
public class SoftMapCache<K, V> extends AbstractMapCache<K, V>{

    public SoftMapCache(CacheRule rule){
        Objects.requireNonNull(rule);
        this.CACHE_RULE = rule;
    }

    public SoftMapCache(){
        this(new CacheRule());
    }

    @Override
    public void put(K key, V value) {
        this.VALUE_MAP.put(key, new SoftReference<>(new Value(value)));
    }
}
