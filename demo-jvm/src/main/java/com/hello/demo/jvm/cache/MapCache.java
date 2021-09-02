package com.hello.demo.jvm.cache;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author: zhaohw
 * @date: 2021.09.01 下午 4:29
 */
public interface MapCache<K, V> {

    V get(K key);

    void put(K key, V value);

    Set<K> keySet();

    default V getOrDefault(K key, Supplier<V> supplier){
        V value0 = this.get(key);
        return Objects.nonNull(value0) ? value0 : supplier.get();
    }

    default V getOrDefaultPut(K key, Supplier<V> supplier){
        V value = this.getOrDefault(key, supplier);
        this.put(key, value);
        return value;
    }

    default void forEach(BiConsumer<? super K, ? super V> consumer){
        Objects.requireNonNull(consumer);
        for (K key : this.keySet()) {
            V value = this.get(key);
            consumer.accept(key, value);
        }
    }
}
