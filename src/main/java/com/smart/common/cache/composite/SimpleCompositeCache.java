package com.smart.common.cache.composite;

import com.smart.common.cache.Cache;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleCompositeCache implements Cache {

    private Logger log = Logger.getLogger(SimpleCompositeCache.class);
    private List<Cache> cacheList;

    public SimpleCompositeCache(Collection<Cache> caches) {
        this.cacheList = new ArrayList<Cache>(caches);
    }

    @Override
    public String getName() {
        return cacheList.get(0).getName();
    }

    @Override
    public Object get(Object key) {
        return getCacheValue(key, 0);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return (T) getCacheValue(key, 0);
    }

    @Override
    public void put(Object key, Object value) {
        for (Cache cache : cacheList) {
            cache.put(key, value);
        }
    }

    @Override
    public void delete(Object key) {
        for (Cache cache : cacheList) {
            cache.delete(key);
        }
    }

    @Override
    public void clear() {
        for (Cache cache : cacheList) {
            cache.clear();
        }
    }

    protected Object getCacheValue(Object key, int i) {
        Object value = cacheList.get(i).get(key);
        if (value == null) {
            if (i == cacheList.size() - 1) {
                return null;
            }
            value = getCacheValue(key, i + 1);
            if (value != null) {
                cacheList.get(i).put(key, value);
            }
        }

        return value;
    }

    @Override
    public String toString() {
        return "SimpleCompositeCache{" +
                "cacheList=" + cacheList +
                '}';
    }
}
