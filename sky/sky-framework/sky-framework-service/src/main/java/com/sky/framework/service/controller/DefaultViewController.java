package com.sky.framework.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 2020-12-14 014 11:48
 */
@RestController
public class DefaultViewController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/")
    public Map<String, Object> indexView() {
        Map<String, Object> map = new HashMap<>();
        map.put("spring.application.name", getProperty("spring.application.name"));
        map.put("spring.profiles.active", getProperty("spring.profiles.active"));
        return map;
    }

    public <T> T getBeans(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    public String getProperty(String key) {
        return getProperty(key, "");
    }

    public String getProperty(String key, String defaultValue) {
        Environment environment = getBeans(Environment.class);
        return environment.getProperty(key, defaultValue);
    }
}
