package com.smart.common.cache.composite;

import com.smart.AbstractBaseTest;
import com.smart.common.cache.Cache;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class SimpleCompositeCacheManagerTest extends AbstractBaseTest{

    @Resource(name = "simpleCompositeCacheManager")
    private SimpleCompositeCacheManager simpleCompositeCacheManager;

    @Test
    public void getCacheTest(){
        Cache cache1 = simpleCompositeCacheManager.getCache("configCache");
        Cache cache2 = simpleCompositeCacheManager.getCache("configCache");
        Assert.assertEquals("configCache", cache1.getName());
        Assert.assertEquals(cache1, cache2);
        cache1.put("test", "test");
        Assert.assertEquals("test", cache1.get("test"));
    }
}
