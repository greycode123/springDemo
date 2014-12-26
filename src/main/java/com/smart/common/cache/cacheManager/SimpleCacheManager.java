package com.smart.common.cache.cacheManager;

import com.smart.common.cache.Cache;
import com.smart.common.cache.CacheManager;
import com.smart.common.cache.map.ConcurrentMapCache;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleCacheManager implements CacheManager {

    private ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Override
    public Cache getCache(String name) {
        return caches.get(name);
    }

    public void setCacheNames(Collection<String> cacheNames) {
        if (cacheNames == null) return;

        for (String name : cacheNames) {
            this.caches.put(name, new ConcurrentMapCache(name));
        }
    }

    public Collection<String> getCacheNames() {
        return Collections.unmodifiableCollection(this.caches.keySet());
    }

}
