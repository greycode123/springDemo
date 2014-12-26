package com.smart.base.service;

import com.smart.base.dao.BaseDao;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractBaseService<T, ID extends Serializable> implements BaseService<T, ID> {

    protected BaseDao<T, ID> baseDao;
    protected boolean cacheFlag = false;

    @Override
    public T get(ID id) {
        if (cacheFlag) {

        }
        return baseDao.get(id);
    }

    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    public int update(T t) {
        return baseDao.update(t);
    }

    @Override
    public void delete(ID id) {
        baseDao.delete(id);
    }

    @Override
    public void saveOrUpdate(T t) {
        if (getCount() == 0) {
            save(t);
        } else {
            update(t);
        }
    }

    @Override
    public int getCount() {
        return baseDao.getCount();
    }

    @Override
    public List<T> getPage(int startIndex, int count) {
        return baseDao.getPage(startIndex, count);
    }

    @Override
    public List<T> getAll() {
        return baseDao.getAll();
    }

    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }
}
