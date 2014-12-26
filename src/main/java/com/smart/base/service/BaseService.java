package com.smart.base.service;

import java.util.List;

public interface BaseService<T, ID> {
    T get(ID id);

    void save(T t);

    int update(T t);

    void delete(ID id);

    void saveOrUpdate(T t);

    int getCount();

    List<T> getPage(int startIndex, int count);

    List<T> getAll();
}
