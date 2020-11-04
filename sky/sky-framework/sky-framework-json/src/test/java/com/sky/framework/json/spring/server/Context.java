package com.sky.framework.json.spring.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.framework.json.spring.JsonViewSupportFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan({"com.sky.framework.json.spring.server"})
public class Context implements WebMvcConfigurer {

    @Bean
    public JsonViewSupportFactoryBean views() {
        return new JsonViewSupportFactoryBean(JacksonConfiguration.configureJackson(new ObjectMapper()), DefaultViewFactory.instance());
    }
}
