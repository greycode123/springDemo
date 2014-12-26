package com.smart.common.cache.ehcache;


import com.smart.common.cache.Cache;
import com.smart.common.cache.CacheManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class EhcacheCacheManager implements CacheManager {

    private net.sf.ehcache.CacheManager ehCacheManager;
    private ConcurrentHashMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    public EhcacheCacheManager(net.sf.ehcache.CacheManager ehCacheManager) {
        this.ehCacheManager = ehCacheManager;
        loadCaches();
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = caches.get(name);
        if(cache == null){
            net.sf.ehcache.Cache ehcache = ehCacheManager.getCache(name);
            this.caches.put(name, new EhcacheCache(ehcache));
            cache = this.caches.get(name);
        }
        return cache;
    }

    @Override
    public Collection<String> getCacheNames() {
        return new ArrayList<String>(Arrays.asList(ehCacheManager.getCacheNames()));
    }

    private void loadCaches() {
        String[] cacheNames = ehCacheManager.getCacheNames();

        for (int i = 0; i < cacheNames.length - 1; i++) {
            this.caches.put(cacheNames[i], new EhcacheCache(ehCacheManager.getCache(cacheNames[i])));
        }
    }
}
