package com.smart.common.cache.composite;


import com.smart.common.cache.Cache;
import com.smart.common.cache.CacheManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleCompositeCacheManager implements CacheManager {

    private List<CacheManager> cacheManagerList = new ArrayList<CacheManager>();
    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();

    @Override
    public Cache getCache(String name) {
        Cache cache = cacheMap.get(name);

        if(cache == null){
            List<Cache> caches = new ArrayList<Cache>();
            for (CacheManager cacheManager : cacheManagerList) {
                Cache tempCache = cacheManager.getCache(name);
                if (tempCache != null) {
                    caches.add(tempCache);
                }
            }
            cacheMap.put(name, new SimpleCompositeCache(caches));
            cache = cacheMap.get(name);
        }

        return cache;
    }

    @Override
    public Collection<String> getCacheNames() {
        List<String> cachesNames = new ArrayList<String>();
        for (CacheManager cacheManager : cacheManagerList) {
            cachesNames.addAll(cacheManager.getCacheNames());
        }
        return cachesNames;
    }

    public void setCacheManagers(Collection<CacheManager> cacheManagerList) {
        this.cacheManagerList =  new ArrayList<CacheManager>(cacheManagerList);
    }
}
