package com.smart.config.dao.impl;

import com.smart.base.dao.BaseIbatisDao;
import com.smart.config.dao.ConfigDao;
import com.smart.config.domain.Config;
import org.springframework.stereotype.Repository;

@Repository("configDao")
public class ConfigDaoImpl extends BaseIbatisDao<Config, String> implements ConfigDao{

}
