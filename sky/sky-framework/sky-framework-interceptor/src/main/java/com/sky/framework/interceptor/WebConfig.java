package com.sky.framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @date 2020-11-05 005 17:54
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ContextInterceptor contextInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(contextInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/*.html", "/webjars/**", "/swagger-resources/**");
    }

    @Bean
    public FilterRegistrationBean<ContextFilter> getRequestFilter() {
        final FilterRegistrationBean<ContextFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new ContextFilter());
        filter.setName("ContextFilter");
        filter.addUrlPatterns("/*");
        return filter;
    }
}
