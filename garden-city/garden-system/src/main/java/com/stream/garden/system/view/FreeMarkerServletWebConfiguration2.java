package com.stream.garden.system.view;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author garden
 * @date 2019-09-30 11:39
 */
@Configuration
public class FreeMarkerServletWebConfiguration2 implements ApplicationContextAware {

    private FreeMarkerProperties properties;

    protected FreeMarkerServletWebConfiguration2(FreeMarkerProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(name = "freeMarkerViewResolver2")
    public FreeMarkerViewResolver2 freeMarkerViewResolver2() {
        FreeMarkerViewResolver2 resolver = new FreeMarkerViewResolver2();
        properties.applyToMvcViewResolver(resolver);
        return resolver;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FreeMarkerViewCache.init(applicationContext);
    }
}
