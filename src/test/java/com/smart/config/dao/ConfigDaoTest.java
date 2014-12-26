package com.smart.config.dao;

import com.smart.AbstractBaseTest;
import com.smart.config.domain.Config;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigDaoTest extends AbstractBaseTest {

    @Resource(name = "configDao")
    private ConfigDao configDao;

    @Test
    public void get() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("key", "isRunDispatchJobFlag");

        Config config = configDao.get("isRunDispatchJobFlag");
        Assert.assertNotNull(config);
        Assert.assertEquals(config.getKey(), "isRunDispatchJobFlag");
    }

    @Test
    public void save() {
        Config config = new Config();
        config.setKey("test1");
        config.setValue("test2");
        config.setDescription("test3");
        configDao.delete(config.getKey());
        configDao.save(config);

        Config config2 = configDao.get("test1");
        Assert.assertNotNull(config2);
        Assert.assertEquals(config2.getKey(), "test1");
        Assert.assertEquals(config2.getValue(), "test2");
        Assert.assertEquals(config2.getDescription(), "test3");
    }

    @Test
    public void update() {
        Config config = new Config();
        config.setKey("test1");
        config.setValue("test2");
        config.setDescription("test3");
        configDao.update(config);
    }

    @Test
    public void saveOrUpdate() {
        Config config = new Config();
        config.setKey("test1");
        config.setValue("test2");
        config.setDescription("test3");
        configDao.saveOrUpdate(config);
    }

    @Test
    public void getTotalCount() {
        Config config = new Config();
        config.setKey("test1");
        int count = configDao.getCount();
    }

    @Test
    public void getPage() {
        configDao.getPage(0,10);
    }

    @Test
    public void getAll() {
        configDao.getAll();
    }

    @Test
    public void getValue() {
        String key = "isRunDispatchJobFlag";
        String value = configDao.get(key).getValue();
        Assert.assertEquals("true", value);
    }

    @Test
    public void getAllConfigList() {
        List<Config> configList = configDao.getAll();
        System.out.println(configList);
    }
}
