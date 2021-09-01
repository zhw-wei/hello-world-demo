package com.hello.demo.jvm.reference.cache;

import com.hello.demo.jvm.reference.ReferenceCache;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.*;

/**
 * 基于软引用定义缓存
 * @author: zhaohw
 * @date: 2021.08.23 上午 10:54
 */
public class SoftReferenceCache<K, V> implements ReferenceCache<K, V> {

    private final Map<K, SoftReference<V>> MAP;

    public SoftReferenceCache(){
        this.MAP = new HashMap<>();
    }

    @Override
    public V get(K key){
        Reference<V> reference = this.MAP.get(key);
        if(Objects.isNull(reference)) return null;
        return reference.get();
    }

    @Override
    public void put(K key, V value){
        this.MAP.put(key, new SoftReference<>(value));
    }

    @Override
    public int size() {
        return this.MAP.size();
    }

    @Override
    public Set<K> keySet() {
        return this.MAP.keySet();
    }
}
