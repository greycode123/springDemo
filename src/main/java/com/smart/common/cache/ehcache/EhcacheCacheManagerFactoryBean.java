package com.smart.common.cache.ehcache;


import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class EhcacheCacheManagerFactoryBean implements FactoryBean<EhcacheCacheManager>, InitializingBean, DisposableBean {

    private EhcacheCacheManager cacheManager;
    private net.sf.ehcache.CacheManager ehCacheManager;
    private Resource configLocation;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            ehCacheManager = net.sf.ehcache.CacheManager.newInstance(configLocation.getInputStream());
        } catch (CacheException e) {
            e.printStackTrace();
            throw new RuntimeException("ehCacheManager init error");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("ehCacheManager init error");
        }

        cacheManager = new EhcacheCacheManager(ehCacheManager);
    }

    @Override
    public EhcacheCacheManager getObject() throws Exception {
        return cacheManager;
    }

    @Override
    public Class<?> getObjectType() {
        return EhcacheCacheManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        if (ehCacheManager != null) {
            ehCacheManager.shutdown();
        }
    }

    public CacheManager getEhCacheManager() {
        return ehCacheManager;
    }

    public void setEhCacheManager(CacheManager ehCacheManager) {
        this.ehCacheManager = ehCacheManager;
    }

    public Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }
}
