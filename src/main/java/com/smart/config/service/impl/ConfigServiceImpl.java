package com.smart.config.service.impl;

import com.smart.base.service.AbstractBaseService;
import com.smart.common.cache.CacheManager;
import com.smart.config.dao.ConfigDao;
import com.smart.config.domain.Config;
import com.smart.config.service.ConfigService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("configService")
public class ConfigServiceImpl extends AbstractBaseService<Config, String> implements ConfigService {

    public static final String CONFIG_CACHE = "configCache";
    public static final String ALL_CONFIG_LIST_CACHE_NAME = "allConfigList";

    private Logger log = Logger.getLogger(getClass());

    @Resource(name = "cacheManager")
    private CacheManager cacheManager;

    @Override
    public Config get(String key) {
        Config config = cacheManager.getCache(CONFIG_CACHE).get(key, Config.class);
        log.debug("config=" + config);
        if (config == null) {
            config = super.get(key);
            cacheManager.getCache(CONFIG_CACHE).put(key, config);
            log.debug("put value to cache:key=" + key + ", value=" + config);
        }

        return config;
    }

    @Override
    public List<Config> getAll() {
        List<Config> allConfigList = (List<Config>) cacheManager.getCache(CONFIG_CACHE).get(ALL_CONFIG_LIST_CACHE_NAME);
        if (allConfigList == null) {
            allConfigList = super.getAll();
            cacheManager.getCache(CONFIG_CACHE).put(ALL_CONFIG_LIST_CACHE_NAME, allConfigList);
        }
        return allConfigList;
    }

    @Override
    public String getValueString(String key) {
        return get(key).getValue();
    }

    @Override
    public boolean getValueBoolean(String key) {
        return BooleanUtils.toBoolean(get(key).getValue());
    }

    @Resource(name = "configDao")
    public void setConfigDao(ConfigDao configDao) {
        super.setBaseDao(configDao);
    }

}
