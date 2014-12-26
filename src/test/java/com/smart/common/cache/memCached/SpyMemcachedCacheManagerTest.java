package com.smart.common.cache.memCached;

import com.smart.AbstractBaseTest;
import com.smart.common.cache.Cache;
import com.smart.common.cache.memcached.SpyMemcachedCacheManager;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class SpyMemcachedCacheManagerTest extends AbstractBaseTest{

    @Resource(name = "spyMemcachedCacheManager")
    private SpyMemcachedCacheManager spyMemcachedCacheManager;

    @Test
    public void getCacheTest(){
        Cache cache = spyMemcachedCacheManager.getCache("configCache");
        Assert.assertEquals("configCache", cache.getName());

        cache.put("test", "test");
        Assert.assertEquals("test", cache.get("test"));

        cache.delete("test");
        Assert.assertEquals(null, cache.get("test"));
    }
}
