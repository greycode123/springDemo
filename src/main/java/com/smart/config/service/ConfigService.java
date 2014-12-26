package com.smart.config.service;

import com.smart.base.service.BaseService;
import com.smart.config.domain.Config;

public interface ConfigService extends BaseService<Config, String> {

    String getValueString(String key);

    boolean getValueBoolean(String key);
}
