package com.hello.demo.jvm.cache;

import lombok.Getter;

import java.lang.ref.Reference;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author: zhaohw
 * @date: 2021.09.01 下午 4:34
 */
public abstract class AbstractMapCache<K, V> implements MapCache<K, V> {
    //缓存内容
    protected Map<K, Reference<Value<V>>> VALUE_MAP = new HashMap<>();

    //缓存规则
    protected CacheRule CACHE_RULE;

    private Supplier<Long> CURRENT_SECOND = () -> ZonedDateTime.now().toEpochSecond();

    @Override
    public V get(K key) {
        Reference<Value<V>> reference = this.VALUE_MAP.get(key);
        if (Objects.isNull(reference)) return null;
        Value<V> value = reference.get();

        if (this.cacheInRule(value)) {
            return value.getValue();
        }else{
            VALUE_MAP.remove(key);
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        return VALUE_MAP.keySet();
    }

    private boolean cacheInRule(Value<V> value) {
        return value.getTime() >
                (this.CURRENT_SECOND.get() - this.CACHE_RULE.getExpireTime());
    }

    protected class Value <T>{

        @Getter
        private long time;

        @Getter
        private T value;

        public Value(T value){
            this.value = value;
            this.time = CURRENT_SECOND.get();
        }
    }
}
