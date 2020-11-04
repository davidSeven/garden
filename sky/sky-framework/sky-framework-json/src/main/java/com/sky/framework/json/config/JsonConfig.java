package com.sky.framework.json.config;

import com.sky.framework.json.spring.JsonViewSupportFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date 2020-11-04 004 11:15
 */
@Configuration
public class JsonConfig {

    @Bean
    public JsonViewSupportFactoryBean jsonViewSupportFactoryBean() {
        return new JsonViewSupportFactoryBean();
    }
}
