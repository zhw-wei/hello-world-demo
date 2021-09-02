package com.hello.demo.jvm.cache;

import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * @author: zhaohw
 * @date: 2021.09.01 下午 4:56
 */
public class WeakMapCache<K, V> extends AbstractMapCache<K, V>{

    public WeakMapCache(CacheRule rule){
        Objects.requireNonNull(rule);
        this.CACHE_RULE = rule;
    }

    public WeakMapCache(){
        this(new CacheRule());
    }

    @Override
    public void put(K key, V value) {
        this.VALUE_MAP.put(key, new WeakReference<>(new Value(value)));
    }
}
