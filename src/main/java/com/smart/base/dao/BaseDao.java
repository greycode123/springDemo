package com.smart.base.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, ID extends Serializable> {

    T get(ID id);

    void save(T t);

    int update(T t);

    void delete(ID id);

    void saveOrUpdate(T t);

    int getCount();

    List<T> getPage(int startIndex, int count);

    List<T> getAll();
}
