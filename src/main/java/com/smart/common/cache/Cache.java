package com.smart.common.cache;

public interface Cache {

    String getName();

    Object get(Object key);

    <T> T get(Object key, Class<T> type);

    void put(Object key, Object value);

    void delete(Object key);

    void clear();
}
