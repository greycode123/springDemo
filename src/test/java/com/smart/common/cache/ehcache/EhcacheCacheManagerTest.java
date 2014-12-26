package com.smart.common.cache.ehcache;

import com.smart.AbstractBaseTest;
import com.smart.common.cache.Cache;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class EhcacheCacheManagerTest extends AbstractBaseTest {

    @Resource(name = "ehcacheCacheManager")
    private EhcacheCacheManager ehcacheCacheManager;

    @Test
    public void getCacheTest() {
        Cache cache = ehcacheCacheManager.getCache("configCache");
        Assert.assertEquals("configCache", cache.getName());
        cache.put("test", "test");
        Assert.assertEquals("test", cache.get("test"));
    }

}
