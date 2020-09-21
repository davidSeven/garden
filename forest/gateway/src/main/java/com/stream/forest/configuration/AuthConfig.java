package com.stream.forest.configuration;

import com.stream.forest.filter.GlobalAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 鉴权过滤器的配置<br>
 */
@Configuration
public class AuthConfig {

    @Bean
    public GlobalAuthFilter globalAuthFilter() {
        return new GlobalAuthFilter();
    }
}
