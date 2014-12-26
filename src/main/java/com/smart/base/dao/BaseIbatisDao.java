package com.smart.base.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseIbatisDao<T, ID extends Serializable> extends SqlMapClientDaoSupport implements BaseDao<T, ID> {

    protected String tableName;

    public BaseIbatisDao() {
        Class entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        tableName = entityClass.getSimpleName().toUpperCase();
    }

    @Override
    public T get(ID id) {
        Map<String, Object> getMap = new HashMap<String, Object>();
        getMap.put("id", id);
        return (T) super.getSqlMapClientTemplate().queryForObject("SELECT-" + tableName, getMap);
    }

    @Override
    public void save(T t) {
        super.getSqlMapClientTemplate().insert("INSERT-" + tableName, t);
    }

    @Override
    public int update(T t) {
        return super.getSqlMapClientTemplate().update("UPDATE-" + tableName, t);
    }

    @Override
    public void delete(ID id) {
        Map<String, Object> getMap = new HashMap<String, Object>();
        getMap.put("id", id);
        super.getSqlMapClientTemplate().delete("DELETE-" + tableName, getMap);
    }

    @Override
    public void saveOrUpdate(T t) {
    }

    @Override
    public int getCount() {
        return (Integer) super.getSqlMapClientTemplate().queryForObject("SELECT-" + tableName + "-COUNT");
    }

    @Override
    public List<T> getPage(int startIndex, int count) {
        return super.getSqlMapClientTemplate().queryForList("SELECT-" + tableName, startIndex, count);
    }

    @Override
    public List<T> getAll() {
        return super.getSqlMapClientTemplate().queryForList("SELECT-" + tableName);
    }

    @Autowired
    @Qualifier("sqlMapClient")
    public void setSqlMapClientForAutowired(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }
}
