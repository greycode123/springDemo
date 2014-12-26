package com.smart.common.cache;

import com.smart.common.cache.cacheManager.SimpleCacheManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleCacheManagerTest {

    @Autowired
    private SimpleCacheManager simpleCacheManager;

    @Test
    public void getCache(){
        simpleCacheManager.getCache("default");
    }
}
