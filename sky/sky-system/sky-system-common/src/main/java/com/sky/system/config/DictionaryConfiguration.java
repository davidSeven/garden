package com.sky.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DictionaryConfiguration {

    @Bean
    public DictionaryBeanPostProcessor dictionaryBeanPostProcessor() {
        return new DictionaryBeanPostProcessor();
    }
}
