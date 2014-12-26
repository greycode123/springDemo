package com.smart.config.service;


import com.smart.AbstractBaseTest;
import com.smart.config.domain.Config;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConfigServiceTest extends AbstractBaseTest {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private ConfigService configService;

    @Test
    public void getValue() {
        String key = "isRunDispatchJobFlag";
        String value = configService.getValueString(key);
        Assert.assertEquals("true", value);

        value = configService.getValueString(key);
        Assert.assertEquals("true", value);
    }

    @Test
    public void getAllConfigList() {
        List<Config> configList = configService.getAll();
        log.info("configList=" + configList);

    }
}
