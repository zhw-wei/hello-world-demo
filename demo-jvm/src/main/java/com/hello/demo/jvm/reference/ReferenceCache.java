package com.hello.demo.jvm.reference;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author: zhaohw
 * @date: 2021.08.23 上午 11:29
 */
public interface ReferenceCache<K, V> {

    V get(K key);

    void put(K key, V value);

    int size();

    Set<K> keySet();

    default V getOrDefault(K key, V defaultValue) {
        return this.getOrDefault(key, () -> defaultValue);
    }

    default V getOrDefault(K key, Supplier<V> supplier) {
        V value = this.get(key);
        if (Objects.isNull(value))
            value = supplier.get();
        return value;
    }

    default void forEach(BiConsumer<? super K, ? super V> con){
        Objects.requireNonNull(con);

        for (K key : this.keySet()) {
            V value = this.get(key);
            con.accept(key, value);
        }
    }
}
