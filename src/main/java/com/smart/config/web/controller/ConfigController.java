package com.smart.config.web.controller;

import com.smart.config.domain.Config;
import com.smart.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "/searchAllConfig.do", method = RequestMethod.GET)
     public String searchAllConfig(ModelMap model)
    {
        List<Config> configList = configService.getAll();
        model.put("configList", configList);

        return "searchAllConfig";
    }

    @RequestMapping(value = "/searchAllConfig2.do", method = RequestMethod.GET)
    public String searchAllConfig2(ModelMap model)
    {
        List<Config> configList = configService.getAll();
        model.put("configList", configList);

        return "searchAllConfig";
    }
}
