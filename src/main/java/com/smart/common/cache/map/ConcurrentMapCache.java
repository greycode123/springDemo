package com.smart.common.cache.map;

import com.smart.common.cache.Cache;
import com.smart.common.cache.CacheEntity;

public class ConcurrentMapCache implements Cache {

    private String name;
    private LRUCache<Object, Object> store = new LRUCache<Object, Object>();

    public ConcurrentMapCache(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object get(Object key) {
        return getCacheValue(key);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return (T) getCacheValue(key);
    }

    @Override
    public void put(Object key, Object value) {
        this.store.put(key, new CacheEntity(key, value));
    }

    @Override
    public void delete(Object key) {
        this.store.remove(key);
    }

    @Override
    public void clear() {
        this.store.clear();
    }

    protected Object getCacheValue(Object key) {
        CacheEntity cacheEntity = (CacheEntity) store.get(key);
        if (cacheEntity == null) return null;

        if (cacheEntity.isExpired()) {
            this.store.remove(key);
            return null;
        }

        Object cacheValue = cacheEntity.getValue();
        return cacheValue;
    }
}
