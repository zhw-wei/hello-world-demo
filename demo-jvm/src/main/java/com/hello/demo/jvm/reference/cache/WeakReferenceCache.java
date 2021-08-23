package com.hello.demo.jvm.reference.cache;

import com.hello.demo.jvm.reference.ReferenceCache;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 基于弱引用定义缓存
 * @author: zhaohw
 * @date: 2021.08.23 上午 10:54
 */
public class WeakReferenceCache<K, V> implements ReferenceCache<K, V> {

    private final Map<K, WeakReference<V>> MAP = new HashMap<>();

    @Override
    public V get(K key){
        return this.MAP.get(key).get();
    }

    @Override
    public void put(K key, V value){
        this.MAP.put(key, new WeakReference<>(value));
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
