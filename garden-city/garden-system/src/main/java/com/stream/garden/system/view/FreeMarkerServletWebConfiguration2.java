package com.stream.garden.system.view;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author garden
 * @date 2019-09-30 11:39
 */
@Configuration
public class FreeMarkerServletWebConfiguration2 {

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
}
