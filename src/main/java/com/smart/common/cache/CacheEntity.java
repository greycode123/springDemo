package com.smart.common.cache;

public class CacheEntity {
    private Object key;
    private Object value;
    private long expiration; //20s
    private long expirationTime;

    public CacheEntity(Object key, Object value) {
       this(key, value, 20);
    }

    public CacheEntity(Object key, Object value, long expiration) {
        this.key = key;
        this.value = value;
        this.expiration = expiration;
        this.expirationTime = System.currentTimeMillis() + expiration * 1000;
    }

    public boolean isExpired(){
        return System.currentTimeMillis() >= expirationTime;
    }

    @Override
    public String toString() {
        return "CacheEntity{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", expiration=" + expiration +
                '}';
    }

    public Object getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }
}
