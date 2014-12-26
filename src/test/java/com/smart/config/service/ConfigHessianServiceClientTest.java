package com.smart.config.service;


import com.smart.AbstractControllerTest;
import com.smart.config.domain.Config;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class ConfigHessianServiceClientTest extends AbstractControllerTest {

    @Autowired
    private ConfigService configService;

    @Test
    public void getValue() {
        String key = "isRunDispatchJobFlag";
        String value = configService.getValueString(key);
        Assert.assertEquals("true", value);
    }

    @Test
    public void getAllConfigList() {
        List<Config> configList = configService.getAll();
    }
}
