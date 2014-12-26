package com.smart.common.cache.memcached;

import com.smart.common.cache.Cache;
import net.spy.memcached.MemcachedClient;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

public class SpyMemcachedCache implements Cache {

    private Logger log = Logger.getLogger(SpyMemcachedCache.class);
    private String name;
    private MemcachedClient memcachedClient;

    public SpyMemcachedCache() {
    }

    public SpyMemcachedCache(String name, MemcachedClient memcachedClient) {
        this.name = name;
        this.memcachedClient = memcachedClient;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object get(Object key) {
        Assert.notNull(key, "key can't be null");
        Object value = memcachedClient.get(getCacheName(key));
        log.debug("cache \""+ key + "\"=" + value +" in " + this);
        return value;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Assert.notNull(key, "key can't be null");
        Object value = memcachedClient.get(getCacheName(key));
        log.debug("cache \""+ key + "\"=" + value +" in " + this);
        return (T) value;
    }

    @Override
    public void put(Object key, Object value) {
        memcachedClient.set(getCacheName(key), 30, value);
        log.debug("put value "+ value + " to cache \""+ key + "\" in " + this);
    }

    @Override
    public void delete(Object key) {
        memcachedClient.delete(getCacheName(key));
    }

    @Override
    public void clear() {
        log.warn("method clear() is not support on MemcachedCache");
        // throw new UnsupportedOperationException("clear() is not support on MemcachedCache");
    }

    protected final String getCacheName(Object key) {
        return this.name + "-" + key.toString();
    }

    @Override
    public String toString() {
        return "SpyMemcachedCache{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }
}
