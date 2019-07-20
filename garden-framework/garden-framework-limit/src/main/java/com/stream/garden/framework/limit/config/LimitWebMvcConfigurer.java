package com.stream.garden.framework.limit.config;

import com.stream.garden.framework.limit.filter.LimitFilter;
import com.stream.garden.framework.web.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author garden
 * @date 2019-07-20 9:57
 */
@Configuration
public class LimitWebMvcConfigurer implements WebMvcConfigurer {

    private final GlobalConfig globalConfig;
    private final LimitConfig limitConfig;

    @Autowired
    public LimitWebMvcConfigurer(GlobalConfig globalConfig, LimitConfig limitConfig) {
        this.globalConfig = globalConfig;
        this.limitConfig = limitConfig;
    }

    @Bean
    public FilterRegistrationBean<LimitFilter> limitFilter() {
        final FilterRegistrationBean<LimitFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new LimitFilter(globalConfig, limitConfig));
        filter.setName("limitFilter");
        filter.addUrlPatterns("/*");
        filter.setOrder(3);
        return filter;
    }
}
