package com.smart.common.cache.memcached;

import net.spy.memcached.MemcachedClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SpyMemcachedClientFactoryBean implements FactoryBean<MemcachedClient>, InitializingBean, DisposableBean {
    ;
    private MemcachedClient memcachedClient;
    private String hostName = "127.0.0.1";
    private int port = 11211;
    private int time = 30;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            memcachedClient = new MemcachedClient(new InetSocketAddress(hostName, port));
        } catch (IOException e) {
            throw new RuntimeException("连接memcache服务端失败。");
        }
    }

    @Override
    public MemcachedClient getObject() throws Exception {
        return memcachedClient;
    }

    @Override
    public Class<?> getObjectType() {
        return MemcachedClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        if (memcachedClient != null)
            memcachedClient.shutdown();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
