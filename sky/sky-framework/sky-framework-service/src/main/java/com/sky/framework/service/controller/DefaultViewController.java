package com.sky.framework.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 2020-12-14 014 11:48
 */
@RestController
public class DefaultViewController {
    private final Logger logger = LoggerFactory.getLogger(DefaultViewController.class);

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/")
    public Map<String, Object> indexView() {
        Map<String, Object> map = new HashMap<>();
        map.put("spring.application.name", getProperty("spring.application.name"));
        map.put("spring.profiles.active", getProperty("spring.profiles.active"));
        return map;
    }

    @SuppressWarnings({"unchecked"})
    @RequestMapping(method = {RequestMethod.GET}, value = "/requestMapping")
    public void requestMapping() {
        // RequestMappingHandlerMapping.class;
        // RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // Map<RequestMappingInfo, HandlerMethod> handlerMethods1 = requestMappingHandlerMapping.getHandlerMethods();
        Map<String, AbstractHandlerMethodMapping> handlerMethodMappingMap = applicationContext.getBeansOfType(AbstractHandlerMethodMapping.class);
        handlerMethodMappingMap.forEach((key, value) -> {
            logger.info("key:{}, value:{}", key, value.getHandlerMethods());
        });

        WebMvcEndpointHandlerMapping webMvcEndpointHandlerMapping = applicationContext.getBean(WebMvcEndpointHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods1 = webMvcEndpointHandlerMapping.getHandlerMethods();
        logger.info(String.valueOf(handlerMethods1));

        AbstractHandlerMethodMapping<RequestMappingInfo> objHandlerMethodMapping = (AbstractHandlerMethodMapping<RequestMappingInfo>) applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = objHandlerMethodMapping.getHandlerMethods();
        logger.info(String.valueOf(handlerMethods));
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
