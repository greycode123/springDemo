package com.smart.common.cache.memcached;

import com.smart.common.cache.Cache;
import com.smart.common.cache.CacheManager;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SpyMemcachedCacheManager implements CacheManager {

    private Logger log = Logger.getLogger(SpyMemcachedCacheManager.class);
    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();

    @Override
    public Cache getCache(String name) {
        return cacheMap.get(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(cacheMap.keySet());
    }

    public void setCaches(Collection<Cache> caches) {
        log.debug("caches="+ caches);
        for(Cache cache: caches){
            log.debug("cache="+ cache);
           this.cacheMap.put(cache.getName(), cache);
        }
    }
}
