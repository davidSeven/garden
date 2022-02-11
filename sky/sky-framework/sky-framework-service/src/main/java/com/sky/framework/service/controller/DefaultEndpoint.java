package com.sky.framework.service.controller;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.HashMap;
import java.util.Map;

@Component
@RestControllerEndpoint(id = "defaultEndpoint")
public class DefaultEndpoint {

    private final ApplicationContext applicationContext;

    public DefaultEndpoint(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping
    public Map<String, String> endpointMap() {
        WebMvcEndpointHandlerMapping webMvcEndpointHandlerMapping = applicationContext.getBean(WebMvcEndpointHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods1 = webMvcEndpointHandlerMapping.getHandlerMethods();
        Map<String, String> map = new HashMap<>();
        handlerMethods1.forEach((requestMappingInfo, handlerMethod) -> {
            map.put(requestMappingInfo.toString(), handlerMethod.toString());
        });
        return map;
    }
}
