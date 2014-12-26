package com.smart.common.cache.ehcache;

import com.smart.common.cache.Cache;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;

public class EhcacheCache implements Cache {

    private Logger log = Logger.getLogger(EhcacheCache.class);
    private String name;
    private net.sf.ehcache.Cache ehcache;

    public EhcacheCache(net.sf.ehcache.Cache ehcache) {
        this.ehcache = ehcache;
        this.name = ehcache.getName();
    }

    public EhcacheCache(net.sf.ehcache.Cache ehcache, String name) {
        this.ehcache = ehcache;
        this.name = name;
    }

    @Override
    public String getName() {
        return ehcache.getName();
    }

    @Override
    public Object get(Object key) {
        return getCacheValue(key);
    }

    protected Object getCacheValue(Object key) {
        Element element = ehcache.get(key);
        if (element == null) {
            log.debug("cache \""+ key + "\"= null in " + this);
            return null;
        }
        Object value = element.getObjectValue();
        log.debug("cache \"" + key + "\"=" + value + " in " + this);
        return value;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return (T) getCacheValue(key);
    }

    @Override
    public void put(Object key, Object value) {
        Element element = new Element(key, value);
        ehcache.put(element);
        log.debug("put value " + value + " to cache \"" + key + "\" in " + this);
    }

    @Override
    public void delete(Object key) {
        ehcache.remove(key);
    }

    @Override
    public void clear() {
        ehcache.removeAll();
    }

    @Override
    public String toString() {
        return "EhcacheCache{" +
                "name='" + name + '\'' +
                '}';
    }
}
